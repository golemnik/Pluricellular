package com.golem.clearCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class ClearTCellFactory extends AbstractTCellFactory {
    public ClearTCellFactory () {
        super (new Signature(
                "clear",
                "clears all elements from the collection and leave it empty.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of("clear")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        ClearTCommandCell cell = new ClearTCommandCell();
        cell.setCollection(getCollection());
        return cell;
    }
}
