package com.golem.filterGreaterThenVenue;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.ArrayList;
import java.util.List;

public class FilterGreaterVenueTCommandCell extends AbstractTicketCommand {
    private List<String> signature;

    @Override
    public void activate() {
        Venue venue = new Venue();
        venue.setId(0);
        venue.setName(signature.get(7));
        venue.setCapacity(Long.parseLong(signature.get(8))); //v cap
        venue.setType(Venue.VenueType.valueOf(signature.get(9))); //v type
        Address address = new Address();
        address.setStreet(signature.get(10)); // add
        venue.setAddress(address);
        List<String> list = new ArrayList<>();
        list.add("Elements list:\n");
        for (Ticket t : manager.getTicketCollection().getCollection().values()) {
            if (t.getVenue().compareTo(venue) > 0) {
                list.add(t.toReadString() + "\n");
            }
        }
        setAnswer(list);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        this.signature = signature;
        return this;
    }
}
