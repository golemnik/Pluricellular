package com.golem.clientCell.recipient;

import com.golem.clientCell.recipient.user.User;
import com.golem.clientCell.recipient.userfactories.UserQueen;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.basicAbstractions.FakeFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.netCell.containers.DataContainer;
import com.golem.netCell.containers.SignatureContainer;
import com.golem.netCell.containers.UserContainer;
import com.golem.netCell.innerMechanics.AbstractNetConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Scanner;

public class Recipient extends AbstractNetConnection {
    private final RecipientSignatureMechanisms recipientMech = new RecipientSignatureMechanisms();
    private User user = UserQueen.getUser();
    private SocketChannel socketChannel;
    private int port = 60888;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Scanner scanner = new Scanner(System.in);
    @Override
    public void cycle(AbstractTerminal terminal) throws IOException {
        List<String> signatureList;
        AbstractCommand command;

        preloadCommands(terminal);

        terminal.getBroodMother().getFactoryCommands().values().stream()
                .filter(AbstractSystemCellFactory::runAtStart)
                .forEach(x -> {
                    AbstractCommand com = x.create(List.of());
                    com.activate();
                    CellPrinter.setMessage(com.getAnswer().toString());
                });

        try {
            preloadConnection(terminal);


            for (Signature s : recipientMech.getSignatureMap().values()) {
                CellPrinter.setMessage(s.command() + " - " + s.status() + " - " + s.description());
            }
            boolean ex = false;
            do {
                signatureList = recipientMech.signatureToSendCycle(scanner);

                if (!(recipientMech.getSignatureMap().get(signatureList.get(0).split(" ")[0])
                        .status() == SignatureStatus.PROVIDED)) {
                    command = terminal.getBroodMother().createCell(signatureList.get(0).split(" ")[0], signatureList);
                    command.activate();
                    command.getAnswer().forEach(CellPrinter::setMessage);
                    ex = command.exitable();
                    continue;
                }
                oos.writeObject(new UserContainer(user.getLogin(), User.encrypt(user.getPassword()), new DataContainer(signatureList)));
                oos.flush();
                DataContainer dataContainer = safeConvert(ois.readObject());
                if (dataContainer != null && dataContainer.data != null) {
                    CellPrinter.setMessage(String.join("\n", dataContainer.data));
                }
            } while (!ex);
        }
        catch (IOException ioe) {
            throw ioe;
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
        }
        CellPrinter.setMessage("Connection closed.");
    }
    public void preloadCommands (AbstractTerminal terminal) {
        terminal.getBroodMother().getFactoryCommands().values()
                .forEach(x -> recipientMech.getSignatureMap().put(x.creationCommand(), x.getSignature()));
    }
    public void preloadConnection(AbstractTerminal terminal) throws IOException, ClassNotFoundException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", port));
        oos = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        ois = new ObjectInputStream(socketChannel.socket().getInputStream());
        SignatureContainer container = safeConvert(ois.readObject());
        recipientMech.updateSignatureMap(container.getSignatures());
        container.getSignatures()
                .forEach(x -> terminal.getBroodMother()
                        .addFactory(new FakeFactory(x.command(), x.description(), x.status()),
                                terminal.getQueenConnections().get(0)));
    }
}
