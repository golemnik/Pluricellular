package com.golem.userLoad;

import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.serverCell.clients.Clients;

import java.util.ArrayList;
import java.util.List;

public class LoadCCellFactory extends AbstractCellFactory {
    public LoadCCellFactory() {
        super(new Signature(
                "load_clients",
                "load clients from \"clients.json\" file.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("load_clients")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public boolean runAtStart() {
        return true;
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        LoadCCommandCell cell = new LoadCCommandCell();
        cell.setClients(Clients.getInstance());
        return cell.useSignature(signature);
    }
}
