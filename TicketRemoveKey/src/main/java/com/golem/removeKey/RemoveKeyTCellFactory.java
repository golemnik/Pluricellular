package com.golem.removeKey;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class RemoveKeyTCellFactory extends AbstractTCellFactory {
    public RemoveKeyTCellFactory() {
        super (new Signature(
                "remove_key",
                "remove the object from the collection with the inputted key.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of(
                        "(remove_key -?[1-9]\\d{0,8}|0|214748364[0-7]$?)")),
                new ArrayList<>(List.of(
                        "")),
                new ArrayList<>(List.of(
                        "unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        RemoveKeyTCommandCell cell = new RemoveKeyTCommandCell();
        cell.setCollection(getCollection());
        return cell.useSignature(signature);
    }
}
