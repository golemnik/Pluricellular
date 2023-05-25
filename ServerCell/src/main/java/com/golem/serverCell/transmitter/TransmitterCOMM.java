//package com.golem.serverCell.transmitter;
//
//import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
//import com.golem.core.schemas.providedRealisations.CellPrinter;
//import com.golem.core.schemas.signature.Signature;
//import com.golem.netCell.containers.ContainerType;
//import com.golem.netCell.containers.DataContainer;
//import com.golem.netCell.containers.SignatureContainer;
//import com.golem.netCell.innerMechanics.AbstractNetConnection;
//import com.golem.serverCell.connections.ConnectedClient;
//
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.net.InetSocketAddress;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.util.*;
//
//public class TransmitterCOMM extends AbstractNetConnection {
//    private ServerSocketChannel serverSocketChannel;
//    private Selector selector;
//    private final Map<SelectionKey, ConnectedClient> clients = new HashMap<>();
//
//    private boolean activateServer () {
//        try {
//            selector = Selector.open();
//            serverSocketChannel = ServerSocketChannel.open();
//            serverSocketChannel.bind(new InetSocketAddress("localhost", 60888));
//            serverSocketChannel.configureBlocking(false);
//            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//            return true;
//        }
//        catch (Exception e) {
//            CellPrinter.setMessage(e.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public void cycle(AbstractTerminal terminal) {
//        SocketChannel socket;
//        if (!activateServer()) return;
//        while (true) {
//            try {
//                while (true) {
//                    socket = serverSocketChannel.accept();
//                    if (socket.isConnected()) {
//                        socket.configureBlocking(false);
//                        SelectionKey key = socket.register(selector, SelectionKey.OP_ACCEPT);
//                        clients.put(key, new ConnectedClient(socket, terminal));
//                    }
//
//                    if (selector.select() == 0) {
//                        continue;
//                    }
//
//                    Set keys = selector.selectedKeys();
//                    Iterator itor = keys.iterator();
//                    while (itor.hasNext()) {
//                        SelectionKey selectionKey = (SelectionKey)itor.next();
//                        itor.remove();
//                        if (selectionKey.isAcceptable()) {
//                            ServerSocketChannel ssChannel = (ServerSocketChannel) selectionKey.channel();
//                            SocketChannel sc = ssChannel.accept();
//                        }
//                        // process channel from key here
//                    }
//                }
//            }
//            catch (Exception e) {
//                CellPrinter.setMessage(e.getMessage());
//            }
//        }
//        while (true) {
//            try {
//                do {
//
//                    System.out.print(".");
//                } while (socket == null);
//                while (socket.isConnected()) {
//
//                    DataContainer dataContainer = safeConvert(ois.readObject());
//                    if (!checkSocket(dataContainer, socket)) break;
//
//                    System.out.println(dataContainer.data.toString());
//                }
//                System.out.println("client quieted.");
//                socket.close();
//            } catch (Exception e) {
//                CellPrinter.setMessage(e.getMessage());
//            }
//        }
//    }
//
//    private boolean checkSocket (DataContainer container, SocketChannel socketChannel) throws IOException {
//        if (container == null) {
//            socketChannel.close();
//            return false;
//        }
//        return true;
//    }
//
//    public static void sendSignatures (List<Signature> signatureList, ObjectOutputStream oos) throws IOException {
//        SignatureContainer container = new SignatureContainer(ContainerType.SIGNATURES);
//        container.setSignatures(signatureList);
//        oos.writeObject(container);
//    }
//
//    public static void reply (ObjectOutputStream oos, List<String> message) throws IOException {
//        oos.writeObject(new DataContainer(message));
//    }
//
//}
