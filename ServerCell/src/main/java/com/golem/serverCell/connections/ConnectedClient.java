package com.golem.serverCell.connections;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureMechanics;
import com.golem.core.schemas.signature.SignatureStatus;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import com.golem.netCell.containers.ContainerType;
import com.golem.netCell.containers.DataContainer;
import com.golem.netCell.containers.SignatureContainer;
import com.golem.netCell.containers.UserContainer;
import com.golem.netCell.innerMechanics.Crypt;
import com.golem.serverCell.clients.Clients;
import com.golem.serverCell.transmitter.Transmitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectedClient {

    private static final Logger logger = LogManager.getLogger(ConnectedClient.class);
    private final SocketChannel channel;
    private final AbstractTerminal terminal;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Clients clients;
    public ConnectedClient (SocketChannel channel, AbstractTerminal terminal, Clients clients) {
        this.channel = channel;
        this.terminal = terminal;
        this.clients = clients;
        prepareActions();
    }

    public void prepareActions () {
        try {
            initConnection();
            logger.info("New connected client: {}", channel);
            sendSignatures(SignatureMechanics.signatureList(
                    terminal.getBroodMother(),
                    SignatureStatus.CONNECTED,
                    SignatureStatus.PROVIDED), oos);
        } catch (Exception e) {
            logger.error("Client initialisation failed due preparing actions:", e);
        }
    }

    public void initConnection () {
        try {
            bis = new BufferedInputStream(channel.socket().getInputStream());
            ois = new ObjectInputStream(bis);
            bos = new BufferedOutputStream(channel.socket().getOutputStream());
            oos = new ObjectOutputStream(bos);
        }
        catch (Exception e) {
            logger.error("Client initialisation failed due streams creating:", e);
        }
    }

    public boolean iterate () {
        try {
            DataContainer dataContainer;
            UserContainer userContainer = (UserContainer) ois.readObject();
            if (!clients.checkClient(userContainer.login, userContainer.password)) {
                logger.info("This client is not registered.");
                dataContainer = unfoundClient();
            }
            else {
                dataContainer = userContainer.dataContainer;
            }



            logger.info("Client {} sent command: {}", channel, dataContainer.data.toString());
            AbstractCommand command = terminal.getBroodMother().createCell(dataContainer.data.get(0).split(" ")[0], dataContainer.data);
            command.activate();
            List<String> answer = command.getAnswer();
            if (answer == null) {
                answer = new ArrayList<>(List.of("Message received"));
            }
            Transmitter.reply(oos, answer);
            return true;
        }
        catch (Exception e) {
            logger.error("Iterating failed due:", e);
            return false;
        }
    }

    public boolean checkReadiness () {
        try {
            return bis.available() > 0;
        }
        catch (Exception e) {
            logger.error("Caught: ", e);
            return false;
        }
    }

    public void sendSignatures (List<Signature> signatureList, ObjectOutputStream oos) throws IOException {
        SignatureContainer container = new SignatureContainer(ContainerType.SIGNATURES);
        container.setSignatures(signatureList);
        container.getSignatures().forEach(x -> logger.info("Requested command: {}", x.command()));
        oos.writeObject(container);
        oos.flush();
    }

    private DataContainer unfoundClient () {
        return new DataContainer(new ArrayList<>(List.of("corrupted", "Client was not found")));
    }

}

