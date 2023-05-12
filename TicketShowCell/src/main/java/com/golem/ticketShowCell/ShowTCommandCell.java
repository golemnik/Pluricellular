package com.golem.ticketShowCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

public class ShowTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    public ShowTCommandCell () {

    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        if (collection.getCollection().size() == 0) {
            setAnswer("Collection is empty. Nothing to show.");
            return;
        }
        String answer = "";
        for (Ticket t : collection.getCollection().values()) {
            answer += "- - - -";
            answer += t.toReadString();
        }
        setAnswer(answer);
    }
}
