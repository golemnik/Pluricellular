package com.golem.clientCell.recipient;

import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.containers.DataContainer;
import com.golem.netCell.containers.SignatureContainer;
import com.golem.netCell.innerMechanics.AbstractNetConnection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Recipient extends AbstractNetConnection {
    private final RecipientSignatureMechanisms recipientMech = new RecipientSignatureMechanisms();
    private SocketChannel socketChannel;
    private int port = 60888;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Scanner scanner = new Scanner(System.in);
    @Override
    public void cycle(AbstractTerminal terminal) {
        preloadCommands(terminal);
        try {
            preloadConnection();
            System.out.println("here");
            String input;
            do {
                for (Signature s : recipientMech.getSignatureMap().values()) {
                    CellPrinter.setMessage(s.description());
                }
                oos.writeObject(new DataContainer(recipientMech.signatureToSendCycle(scanner)));
                oos.flush();

                DataContainer dataContainer = safeConvert(ois.readObject());
                if (dataContainer != null) CellPrinter.setMessage(dataContainer.data.toString());

                input = scanner.nextLine();
            } while (!input.equals("close"));
            socketChannel.close();
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
        }
        CellPrinter.setMessage("Connection closed.");
    }

    public void preloadCommands (AbstractTerminal terminal) {
        System.out.println(terminal.getBroodMother().getFactoryCommands().values());
        terminal.getBroodMother().getFactoryCommands().values()
                .forEach(x -> recipientMech.getSignatureMap().put(x.creationCommand(), x.getSignature()));
    }
    public void preloadConnection() throws IOException, ClassNotFoundException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", port));
        System.out.println("here1");
        oos = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        System.out.println("here2");
        ois = new ObjectInputStream(socketChannel.socket().getInputStream());
        System.out.println("here3");
        SignatureContainer container = safeConvert(ois.readObject());
        System.out.println("here4");
        recipientMech.updateSignatureMap(container.getSignatures());
    }
}
