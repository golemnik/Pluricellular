package com.golem.ticketShowCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class ShowTCellFactory extends AbstractTCellFactory {
    public ShowTCellFactory () {
        super (new Signature(
                "show",
                "shows all elements from the collection.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of("show")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        ShowTCommandCell cell = new ShowTCommandCell();
        cell.setCollection(getCollection());
        return cell;
    }
}
