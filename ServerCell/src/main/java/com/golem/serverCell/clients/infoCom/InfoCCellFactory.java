package com.golem.serverCell.clients.infoCom;

import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.serverCell.clients.Clients;

import java.util.ArrayList;
import java.util.List;

public class InfoCCellFactory extends AbstractCellFactory {
    public InfoCCellFactory() {
        super(new Signature(
                "info_clients",
                "show list and stat.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("info_clients")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }
    @Override
    public AbstractCommand create(List<String> signature) {
        InfoCCommandCell cell = new InfoCCommandCell();
        cell.setClients(Clients.getInstance());
        return cell.useSignature(signature);
    }
}
