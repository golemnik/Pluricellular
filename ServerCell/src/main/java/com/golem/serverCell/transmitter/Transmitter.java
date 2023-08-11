package com.golem.serverCell.transmitter;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.core.schemas.signature.SignatureMechanics;
import com.golem.netCell.containers.ContainerType;
import com.golem.netCell.containers.DataContainer;
import com.golem.netCell.containers.SignatureContainer;
import com.golem.netCell.innerMechanics.*;
import com.golem.serverCell.clients.Clients;
import com.golem.serverCell.connections.ConnectedClient;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Transmitter extends AbstractNetConnection {
    private static final Logger logger = LogManager.getLogger(Transmitter.class);
    private ServerSocketChannel serverSocketChannel;
    private final Map<SocketChannel, ConnectedClient> clients = new HashMap<>();
    private final String HOSTNAME = "localhost";
    private final int PORT = 60888;
    private Clients registeredClients = Clients.getInstance();
    private ExecutorService executor;
    private final int POOL_SIZE = 100;
    private boolean activateServer () {
        try {
            executor = Executors.newFixedThreadPool(POOL_SIZE);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(HOSTNAME, PORT));
            serverSocketChannel.configureBlocking(false);
            logger.info("Activating server at {}:{}", HOSTNAME, PORT);
            return true;
        }
        catch (Exception e) {
            logger.error("Server activation failed:", e);
            return false;
        }
    }

    @Override
    public void cycle(AbstractTerminal terminal) {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        AbstractCommand command;
        SocketChannel socket;
        boolean sleep;

        if (!activateServer()) return;
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            logger. info("Deactivating server.");
        }));
        terminal.getBroodMother().getFactoryCommands().values().stream()
                .filter(AbstractSystemCellFactory::runAtStart)
                .forEach(x -> {
                    AbstractCommand com = x.create(List.of());
                    com.activate();
                    logger.info(com.getAnswer());
                });
        boolean ex = false;
        while (!ex) {
            try {
                while (!ex) {
                    socket = serverSocketChannel.accept();
                    if (!(socket == null)) {
                        if (socket.isConnected()) {
                        clients.put(socket, new ConnectedClient(socket, terminal, registeredClients));
                        }
                    }
                    clients.keySet().stream().filter(x -> !x.isConnected()).forEach(clients::remove);
                    sleep = clients.values().stream()
                            .anyMatch(ConnectedClient::checkReadiness);
                    try {
                        executor.execute(() -> clients.values().stream()
                                .filter(ConnectedClient::checkReadiness)
                                .map(ConnectedClient::iterate)
                                .close());
                    } catch (Exception exec) {
                        executor.shutdown();
                    }

                    if (scanner.ready()) {
                        command = SignatureMechanics.consoleInputCycle(scanner, terminal.getBroodMother(), scanner.readLine());
                        command.activate();
                        command.getAnswer().forEach(CellPrinter::setMessage);
                        ex = command.exitable();
                    }
                    if (sleep) {
                        Thread.sleep(10);
                    }
                }
            }
            catch (Exception e) {
                logger.error("Server cycle error found: ", e);
                CellPrinter.setMessage(e.getMessage());
            }
        }
        terminal.getBroodMother().getFactoryCommands().values().stream()
                .filter(AbstractSystemCellFactory::runAtFinish)
                .forEach(x -> {
                    AbstractCommand com = x.create(List.of());
                    com.activate();
                    logger.info(com.getAnswer());
                });
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
        logger.info("Command replay: {}", message);
        oos.writeObject(new DataContainer(message));
        oos.flush();
    }

}
