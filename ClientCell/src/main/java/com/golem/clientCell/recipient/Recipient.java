package com.golem.clientCell.recipient;

import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.containers.DataContainer;
import com.golem.netCell.containers.SignatureContainer;
import com.golem.netCell.innerMechanics.AbstractNetConnection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recipient extends AbstractNetConnection {
    private RecipientSignatureMechanics recipientMech =new RecipientSignatureMechanics();
    private SocketChannel socketChannel;
    private int port = 60888;
    private Scanner scanner = new Scanner(System.in);
    @Override
    public void cycle(AbstractTerminal terminal) {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", port));
            ObjectOutputStream oos = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socketChannel.socket().getInputStream());

            SignatureContainer container = safeConvert(ois.readObject());
            recipientMech.updateSignatureMap(container.getSignatures());
            String input = scanner.nextLine();
            do {
                for (Signature s : recipientMech.getSignatureMap().values()) {
                    CellPrinter.setMessage(s.commandDescription());
                }
                oos.writeObject(new DataContainer(recipientMech.signatureToSendCycle(scanner)));


                DataContainer dataContainer = safeConvert(ois.readObject());
                if (dataContainer != null) CellPrinter.setMessage(dataContainer.data.toString());

                input = scanner.nextLine();
            } while (!input.equals("close"));
            CellPrinter.setMessage("Connection closed.");
            socketChannel.close();
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
        }
    }
}


// сигнатура команды
