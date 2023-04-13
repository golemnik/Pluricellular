package com.golem.ticketCell;

import com.golem.core.schemas.Cell;

public class TicketCell implements Cell {
    @Override
    public void activate() {
        System.out.println("Ticket is here!");
    }
}
