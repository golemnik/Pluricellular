package com.golem.ticketShowCell;

import com.golem.core.schemas.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

public class ShowTCellFactory extends AbstractTCellFactory {
    @Override
    public Cell create() {
        ShowTCommandCell cell = new ShowTCommandCell();
        cell.setCollection(getCollection());
        return cell;
    }

    @Override
    public String creationCommand() {
        return "show";
    }

    @Override
    public String commandDescription() {
        return "shows all elements from the collection.";
    }
}
