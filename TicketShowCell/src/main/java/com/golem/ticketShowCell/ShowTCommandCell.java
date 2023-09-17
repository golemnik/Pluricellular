package com.golem.ticketShowCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.List;

public class ShowTCommandCell extends AbstractTicketCommand {
    public ShowTCommandCell () {

    }

    @Override
    public void activate() {
        if (manager.getTicketCollection() == null) {
            setAnswer(List.of("Collection is not exist."));
            return;
        }
        if (manager.getTicketMap().isEmpty()) {
            setAnswer(List.of("Collection is empty. Nothing to show."));
            return;
        }
        String answer = "";
        for (Ticket t : manager.getTicketMap().values()) {
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
