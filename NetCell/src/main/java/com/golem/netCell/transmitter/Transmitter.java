package com.golem.netCell.transmitter;

import com.golem.core.innerMechanisms.SignatureMechanics;
import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.containers.BaseContainer;
import com.golem.netCell.containers.ContainerType;
import com.golem.netCell.containers.DataContainer;
import com.golem.netCell.containers.SignatureContainer;
import com.golem.netCell.innerMechanics.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Transmitter extends AbstractNetConnection {
    private ServerSocketChannel serverSocketChannel;

    @Override
    public void cycle(AbstractTerminal terminal) {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(60888));
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
            return;
        }
        while (true) {
            try {
                SocketChannel socket = serverSocketChannel.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.socket().getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.socket().getOutputStream());
                sendSignatures(SignatureMechanics.signatureList(terminal.getBroodMother()), oos);
                while (socket.isConnected()) {

                    DataContainer dataContainer = safeConvert(ois.readObject());
                    if (!checkSocket(dataContainer, socket)) break;

                    System.out.println(dataContainer.data.toString());

                    oos.writeObject(new DataContainer(new ArrayList<>(List.of("Message received."))));
                }
                System.out.println("client quieted.");
                socket.close();
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

    private void sendSignatures (List<Signature> signatureList, ObjectOutputStream oos) throws IOException {
        oos.writeObject(new BaseContainer(ContainerType.SIGNATURES, new SignatureContainer(signatureList)));
    }

}
