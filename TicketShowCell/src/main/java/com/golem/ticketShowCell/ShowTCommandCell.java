package com.golem.ticketShowCell;

import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

public class ShowTCommandCell implements Cell {
    private TicketCollection collection;
    public ShowTCommandCell () {

    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        if (collection.getCollection().size() == 0) {
            CellPrinter.setMessage("Collection is empty. Nothing to show.");
            return;
        }
        for (Ticket t : collection.getCollection().values()) {
            CellPrinter.setMessage("- - - -");
            CellPrinter.setMessage(t.toReadString());
        }
    }
}
