package com.golem.clientLoad;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class LoadClientCellFactory extends AbstractSystemCellFactory {
    public LoadClientCellFactory() {
        super(new Signature(
                "cli_load",
                "load client list from \"clients.json\" file.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("cli_load")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public boolean runAtStart() {
        return true;
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        LoadClientCommandCell cell = new LoadClientCommandCell();
        return cell.useSignature(signature);
    }
}

