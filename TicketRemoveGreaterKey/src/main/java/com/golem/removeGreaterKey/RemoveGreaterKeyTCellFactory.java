package com.golem.removeGreaterKey;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketCell.schemas.SignatureRegex;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterKeyTCellFactory extends AbstractTCellFactory {
    public RemoveGreaterKeyTCellFactory() {
        super (new Signature(
                "remove_greater_key",
                "remove all tickets which key is greater than key was inputted during this command.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of(
                        "(remove_greater_key (-" + SignatureRegex.ID + "))"
                        )),
                new ArrayList<>(List.of(
                        ""
                        )),
                new ArrayList<>(List.of(
                        "unsupported arguments"
                        ))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        RemoveGreaterKeyTCommandCell cell = new RemoveGreaterKeyTCommandCell();
        cell.setCollection(getCollection());
        return cell.useSignature(signature);
    }
}
