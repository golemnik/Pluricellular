package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.ticket.Ticket;

public interface CollectionAccess {
    Ticket get (String key); // rules?
    void add (String key, Ticket ticket);
    void delete (String key);
    void delete (Ticket ticket);

//    List<Ticket> getTicketList (); // add rules
//    void sort();
}
