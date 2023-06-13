package com.golem.filterGreaterThenVenue;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.ArrayList;
import java.util.List;

public class FilterGreaterVenueTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    private Venue venue;
    public FilterGreaterVenueTCommandCell() {

    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        List<String> list = new ArrayList<>();
        list.add("Elements list:\n");
        for (Ticket t : collection.getCollection().values()) {
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
        venue.setId(otherMechs.getId(collection));
        venue.setName(signature.get(7));
        venue.setCapacity(Long.parseLong(signature.get(8))); //v cap
        venue.setType(Venue.VenueType.valueOf(signature.get(9))); //v type
        Address address = new Address();
        address.setStreet(signature.get(10)); // add
        venue.setAddress(address);
        return this;
    }
}
