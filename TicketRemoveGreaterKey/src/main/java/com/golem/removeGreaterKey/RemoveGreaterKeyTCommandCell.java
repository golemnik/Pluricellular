package com.golem.removeGreaterKey;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveGreaterKeyTCommandCell extends AbstractTicketCommand {
    private String ticketKey;

    @Override
    public void activate() {
        List<String> list = manager.getTicketCollection().getCollection().keySet()
                .stream()
                .filter(x -> x.compareTo(ticketKey) > 0)
                .toList();
        list.forEach(x -> manager.delete(x));
        setAnswer(List.of("Collection was successfully updated."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        ticketKey = signature.get(0).split(" ")[1];
        return this;
    }
}
