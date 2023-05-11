package com.golem.netCell.recipient;

import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.innerMechanics.AbstractNetConnection;
import com.golem.netCell.innerMechanics.DataContainer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recipient extends AbstractNetConnection {
    private SocketChannel socketChannel;
    private int port = 60888;
    private Scanner scanner = new Scanner(System.in);
    @Override
    public void cycle() {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", port));
            String input = scanner.nextLine();
            do {
                ObjectOutputStream oos = new ObjectOutputStream(socketChannel.socket().getOutputStream());
                oos.writeObject(new DataContainer(new ArrayList<>(List.of(input.split(" ")))));
                ObjectInputStream ois = new ObjectInputStream(socketChannel.socket().getInputStream());

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
