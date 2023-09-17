package com.golem.serverCell.transmitter;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.core.schemas.signature.SignatureMechanics;
import com.golem.informer.Informer;
import com.golem.informer.Level;
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
    boolean ex = false;
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
        SocketChannel socket;

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
        executor.execute(new ConsoleThread(scanner, terminal));
        while (!ex) {
            try {
                while (!ex) {
                    try {
                        socket = serverSocketChannel.accept();
                        if (!(socket == null)) {
                            if (socket.isConnected()) {
                                executor.execute(new ClientsThread(socket, terminal, registeredClients));
                            }
                        }
                    } catch (Exception exec) {
                        executor.shutdown();
                    }
                }
            }
            catch (Exception e) {
                logger.error("Server cycle error found: ", e);
                CellPrinter.setMessage(e.getMessage());
            }
        }
        try {
            serverSocketChannel.close();
            terminal.getBroodMother().getFactoryCommands().values().stream()
                    .filter(AbstractSystemCellFactory::runAtFinish)
                    .forEach(x -> {
                        AbstractCommand com = x.create(List.of());
                        com.activate();
                        logger.info(com.getAnswer());
                    });
            executor.shutdownNow();
        }
        catch (Exception e) {
            Informer.log(Level.INFO, "Server forced shutdown...");
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
        logger.info("Command replay: {}", message);
        oos.writeObject(new DataContainer(message));
        oos.flush();
    }

    class ClientsThread implements Runnable {
        private final SocketChannel socket;
        private final ConnectedClient client;
        public ClientsThread (SocketChannel socket, AbstractTerminal terminal, Clients clients) {
            this.socket = socket;
            client = new ConnectedClient(executor, socket, terminal, clients);
        }
        @Override
        public void run() {
            Informer.log(Level.INFO, "New ClientsThread:");
            try {
                while (socket.isConnected()) {
                    if (client.checkReadiness()) {
                        client.iterate();
                    }
                    else {
                        Thread.sleep(10);
                    }
                }
            }
            catch (Exception e) {
//                logger.error("", e);
                Informer.log(Level.ERROR, "ClientsThread run cycle was interrupted...");
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }

    class ConsoleThread implements Runnable {
        private final BufferedReader scanner;
        private final AbstractTerminal terminal;
        public ConsoleThread (BufferedReader scanner, AbstractTerminal terminal) {
            this.scanner = scanner;
            this.terminal = terminal;
        }

        @Override
        public void run() {
            Informer.log(Level.INFO, "Opened new ConsoleThread");
            try {
                while (!ex) {
                    if (scanner.ready()) {
                        AbstractCommand command = SignatureMechanics
                                .consoleInputCycle(scanner, terminal.getBroodMother(), scanner.readLine());
                        command.activate();
                        command.getAnswer().forEach(CellPrinter::setMessage);
                        ex = command.exitable();
                    }
                }

            } catch (IOException e) {
                logger.error("", e);
            }
        }
    }
}
