package com.golem.ticketCell.collection.ticket;

import java.util.Comparator;

public class InversedTicketTypeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        return -1 * t1.getType().compareTo(t2.getType());
    }
}
