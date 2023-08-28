package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.LinkedHashMap;

public interface CollectionAccess {
    Ticket get (String key);
    void add (String key, Ticket ticket, String login);
    void delete (String key);
    void delete (Ticket ticket);
    boolean checkID (int ID);
    boolean checkKey (String key);
    void clear ();
    TicketCollection getTicketCollection();
    TicketCollection getTicketCollection(String login);
    LinkedHashMap<String, Ticket> getTicketMap();

//    List<Ticket> getTicketList (); // add rules
//    void sort();
}
