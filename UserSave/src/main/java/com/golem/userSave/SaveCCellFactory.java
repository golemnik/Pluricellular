package com.golem.userSave;

import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.serverCell.clients.Clients;

import java.util.ArrayList;
import java.util.List;

public class SaveCCellFactory extends AbstractCellFactory {
    public SaveCCellFactory() {
        super(new Signature(
                "save_clients",
                "save clients to the \"clients.json\" file.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("save_clients")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public boolean runAtFinish() {
        return true;
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        SaveCCommandCell cell = new SaveCCommandCell();
        cell.setClients(Clients.getInstance());
        return cell.useSignature(signature);
    }
}
