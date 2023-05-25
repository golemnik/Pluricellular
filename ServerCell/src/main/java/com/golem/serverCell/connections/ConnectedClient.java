package com.golem.serverCell.connections;

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
import com.golem.serverCell.transmitter.Transmitter;

public class ConnectedClient {
    private final SocketChannel channel;
    private final AbstractTerminal terminal;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public ConnectedClient (SocketChannel channel, AbstractTerminal terminal) {
        this.channel = channel;
        this.terminal = terminal;
        prepareActions();
        }

    public void prepareActions () {
        try {
            initConnection();
            sendSignatures(SignatureMechanics.signatureList(
                    terminal.getBroodMother(),
                    SignatureStatus.CONNECTED,
                    SignatureStatus.PROVIDED), oos);
            System.out.println("here");
        } catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
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
            CellPrinter.setMessage("Client initialisation failed due streams creating:");
            CellPrinter.setMessage(e.getMessage());
        }
    }

    public void iterate () {
        try {
            DataContainer dataContainer = (DataContainer) ois.readObject();
            CellPrinter.setMessage(dataContainer.data.toString());
            Transmitter.reply(oos, new ArrayList<>(List.of("Message received.")));
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
        }
    }

    public boolean checkReadiness () {
        try {
            return bis.available() > 0;
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
            return false;
        }
    }


    public void sendSignatures (List<Signature> signatureList, ObjectOutputStream oos) throws IOException {
        SignatureContainer container = new SignatureContainer(ContainerType.SIGNATURES);
        container.setSignatures(signatureList);
        oos.writeObject(container);
        oos.flush();
    }

}