package com.golem.ticketCell.collection;

import com.golem.ticketCell.collection.ticket.Ticket;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class TicketCollection implements ConsoleRead{
    private static final TicketCollection instance = new TicketCollection();

    public static TicketCollection getInstance() {
        return instance;
    }

    private LocalDate creationDate = LocalDate.now();
    private Map<String, Ticket> collection = new LinkedHashMap<>();

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDate getCreationDate () {
        return creationDate;
    }

    public Map<String, Ticket> getCollection() {
        return collection;
    }

    public String toReadString() {
        return  "Collection:\n" +
                "   Create date: " + creationDate.toString() + "\n" +
                "   Collection type: " + collection.getClass().getSimpleName() + "\n"+
                "   Object amount: " + collection.size() + "\n";
    }
}
