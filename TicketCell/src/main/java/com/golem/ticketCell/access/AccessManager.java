package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

public class AccessManager extends AbstractAccess{
    private final TicketCollection collection = new TicketCollection();
    @Override
    public Ticket get(String key) {
        return collection.getCollection().get(key);
    }

    @Override
    public void add(String key, Ticket ticket) {
        collection.getCollection().put(key, ticket);
    }

    @Override
    public void delete(String key) {
        collection.getCollection().remove(key);
    }

    @Override
    public void delete(Ticket ticket) {
        collection.getCollection()
                .values()
                .remove(ticket);
    }
}
