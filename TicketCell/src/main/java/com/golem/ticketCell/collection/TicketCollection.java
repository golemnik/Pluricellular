package com.golem.ticketCell.collection;

import com.golem.ticketCell.collection.ticket.Ticket;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class TicketCollection implements ConsoleRead{
    private LocalDate creationDate;
    private LinkedHashMap<String, Ticket> collection = new LinkedHashMap<>();

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDate getCreationDate () {
        return creationDate;
    }

    public void setCollection (LinkedHashMap<String, Ticket> collection) {
        this.collection = collection;
    }
    public LinkedHashMap<String, Ticket> getCollection() {
        return collection;
    }
    {
        creationDate=LocalDate.now();
    }
    public TicketCollection() {}
    public TicketCollection(boolean init) {
        if (init) testLoad();
    }
    public void testLoad () {
        creationDate = LocalDate.now();
        collection.put("1", new Ticket(true));
        collection.put("2", new Ticket(true));
        collection.put("3", new Ticket(true));
    }

    public String toReadString() {
        return  "Collection:\n" +
                "   Create date: " + creationDate.toString() + "\n" +
                "   Collection type: " + collection.getClass().getSimpleName() + "\n"+
                "   Object amount: " + collection.size() + "\n";
    }
}
