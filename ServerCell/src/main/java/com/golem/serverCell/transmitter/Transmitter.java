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
import java.util.stream.Collectors;

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
        SocketChannel socket;
        Optional<Boolean> sleep;
        System.out.println(terminal.getBroodMother().getFactoryCommands().keySet()); // todo delete
        if (!activateServer()) return;
        while (true) {
            try {
                while (true) {
                    socket = serverSocketChannel.accept(); // timeout for waiting?
                    if (!(socket == null)) {
                        if (socket.isConnected()) {
                        clients.put(socket, new ConnectedClient(socket, terminal));
                        }
                    }
                    clients.keySet().stream().filter(x -> !x.isConnected()).forEach(clients::remove);
                    sleep = clients.values().stream().filter(ConnectedClient::checkReadiness)
                            .map(ConnectedClient::iterate)
                            .reduce((x, y) -> x || y);
                    if (sleep.isEmpty()) {
                        Thread.sleep(10);
                    }
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
