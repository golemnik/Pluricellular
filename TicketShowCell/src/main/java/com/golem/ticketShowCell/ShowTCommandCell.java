package com.golem.ticketShowCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.List;

public class ShowTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    public ShowTCommandCell () {

    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        if (collection == null) {
            setAnswer(List.of("Collection is not exist."));
            return;
        }
        if (collection.getCollection().size() == 0) {
            setAnswer(List.of("Collection is empty. Nothing to show."));
            return;
        }
        String answer = "";
        for (Ticket t : collection.getCollection().values()) {
            answer += "- - - -";
            answer += t.toReadString();
        }
        setAnswer(List.of(answer));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
