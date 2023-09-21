package com.golem.removeGreaterKey;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.List;

public class RemoveGreaterKeyTCommandCell extends AbstractTicketCommand {
    private String ticketKey;

    @Override
    public void activate() {
        manager.getTicketCollection().getCollection().keySet().removeIf(s -> s.compareTo(ticketKey) > 0);
        setAnswer(List.of("Collection was successfully updated."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        ticketKey = signature.get(0).split(" ")[1];
        return this;
    }
}
