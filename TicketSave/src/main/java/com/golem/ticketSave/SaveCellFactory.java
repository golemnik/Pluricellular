package com.golem.ticketSave;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class SaveCellFactory extends AbstractTCellFactory {
    public SaveCellFactory() {
        super(new Signature(
                "save",
                "save collection to the \"save.json\" file.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("save")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public boolean runAtFinish() {
        return true;
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        SaveTCommandCell cell = new SaveTCommandCell();
        cell.setCollection(getManager());
        return cell.useSignature(signature);
    }
}
