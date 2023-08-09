package com.golem.ticketLoad;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class LoadCellFactory extends AbstractTCellFactory {
    public LoadCellFactory() {
        super(new Signature(
                "load",
                "load collection from \"save.json\" file.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("load")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public boolean runAtStart() {
        return true;
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        LoadTCommandCell cell = new LoadTCommandCell();
        cell.setManager(getManager());
        return cell.useSignature(signature);
    }
}

