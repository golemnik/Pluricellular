package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.exception.NotAddedTException;
import com.golem.ticketCell.exception.NotUpdatedTException;

import java.util.Map;

public interface CollectionAccess {
    Ticket get (String key);
    void add (String key, Ticket ticket, String login) throws NotAddedTException;
    void update (Ticket ticket, String login) throws NotUpdatedTException;
    void delete (String key);
    void delete (Ticket ticket);
    boolean checkID (int ID, String owner);
    boolean checkKey (String key, String owner);
    void clear ();
    void clear (String owner);
    TicketCollection getTicketCollection();
    TicketCollection getTicketCollection(String login);
    Map<String, Ticket> getTicketMap();

//    List<Ticket> getTicketList (); // add rules
//    void sort();
}
