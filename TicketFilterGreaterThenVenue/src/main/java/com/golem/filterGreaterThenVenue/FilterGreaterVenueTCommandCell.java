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
    private Venue venue;
    public FilterGreaterVenueTCommandCell() {
    }

    @Override
    public void activate() {
        List<String> list = new ArrayList<>();
        list.add("Elements list:\n");
        for (Ticket t : manager.getTicketCollection().getCollection().values()) {
            if (venue == null) {
                continue;
            }
            if (t.getVenue().compareTo(venue) > 0) {
                list.add(t.toReadString() + "\n");
            }
        }
        setAnswer(list);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        venue = new Venue();
        venue.setId(manager.newID());
        venue.setName(signature.get(7));
        venue.setCapacity(Long.parseLong(signature.get(8))); //v cap
        venue.setType(Venue.VenueType.valueOf(signature.get(9))); //v type
        Address address = new Address();
        address.setStreet(signature.get(10)); // add
        venue.setAddress(address);
        return this;
    }
}
