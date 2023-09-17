package com.golem.removeGreater;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.List;

public class RemoveGreaterTCommandCell extends AbstractTicketCommand {
    private Ticket ticket;
    private List<String> signature;

    @Override
    public void activate() {
        ticket = new Ticket();
        ticket.setOwner(getLogin());
        ticket.setId(0);
        ticket.setName(signature.get(1)); // t name
        ticket.setPrice(Double.parseDouble(signature.get(2))); // t price
        ticket.setComment(signature.get(3)); // t comment
        ticket.setType(Ticket.TicketType.valueOf(signature.get(4))); // t type
        Coordinates coord = new Coordinates();
        coord.setX(Long.parseLong(signature.get(5))); // t x
        coord.setY(Long.parseLong(signature.get(6))); // t y
        ticket.setCoordinates(coord);
        if (signature.get(7) != null) { // v name
            Venue venue = new Venue();
            venue.setId(0);
            venue.setName(signature.get(7));
            venue.setCapacity(Long.parseLong(signature.get(8))); //v cap
            venue.setType(Venue.VenueType.valueOf(signature.get(9))); //v type
            Address address = new Address();
            address.setStreet(signature.get(10)); // add
            venue.setAddress(address);
            ticket.setVenue(venue);
        }
        manager.getTicketMap().values().removeIf(x -> x.compareTo(ticket) < 0);
        setAnswer(List.of("Collection was successfully updated."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        this.signature = signature;
        return this;
    }
}
