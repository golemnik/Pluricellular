package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

public interface CollectionAccess {
    Ticket get (String key);
    void add (String key, Ticket ticket);
    void delete (String key);
    void delete (Ticket ticket);
    boolean checkID (int ID);
    int newID ();
    void clear ();
    TicketCollection getTicketCollection();


//    List<Ticket> getTicketList (); // add rules
//    void sort();
}
