package com.golem.serverCell.transmitter;

import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.containers.ContainerType;
import com.golem.netCell.containers.DataContainer;
import com.golem.netCell.containers.SignatureContainer;
import com.golem.netCell.innerMechanics.*;
import com.golem.serverCell.connections.ConnectedClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Transmitter extends AbstractNetConnection {
    private ServerSocketChannel serverSocketChannel;
    private final Map<SocketChannel, ConnectedClient> clients = new HashMap<>();

    private boolean activateServer () {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 60888));
            serverSocketChannel.configureBlocking(false);
            return true;
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
            return false;
        }
    }

    @Override
    public void cycle(AbstractTerminal terminal) {
        if (!activateServer()) return;
        while (true) {
            try {
                while (true) {
                    SocketChannel socket = serverSocketChannel.accept();
                    if (!(socket == null)) {
                        if (socket.isConnected()) {
                        clients.put(socket, new ConnectedClient(socket, terminal));
                        }
                    }
                    clients.values().stream().filter(ConnectedClient::checkReadiness).forEach(ConnectedClient::iterate);
                }
            }
            catch (Exception e) {
                CellPrinter.setMessage(e.getMessage());
            }
        }
    }

    private boolean checkSocket (DataContainer container, SocketChannel socketChannel) throws IOException {
        if (container == null) {
            socketChannel.close();
            return false;
        }
        return true;
    }

    public static void sendSignatures (List<Signature> signatureList, ObjectOutputStream oos) throws IOException {
        SignatureContainer container = new SignatureContainer(ContainerType.SIGNATURES);
        container.setSignatures(signatureList);
        oos.writeObject(container);
    }

    public static void reply (ObjectOutputStream oos, List<String> message) throws IOException {
        oos.writeObject(new DataContainer(message));
        oos.flush();
    }

}
