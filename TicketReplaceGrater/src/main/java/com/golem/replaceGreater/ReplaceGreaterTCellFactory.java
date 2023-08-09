package com.golem.replaceGreater;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketCell.schemas.SignatureRegex;

import java.util.ArrayList;
import java.util.List;

public class ReplaceGreaterTCellFactory extends AbstractTCellFactory {
    public ReplaceGreaterTCellFactory() {
        super (new Signature(
                "replace_if_greater",
                "remove all tickets greater then object was inputted during this command    .",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of("^" + "replace_if_greater " + SignatureRegex.ID + "$")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        ReplaceGreaterTCommandCell cell = new ReplaceGreaterTCommandCell();
        cell.setManager(getManager());
        return cell.useSignature(signature);
    }
}
