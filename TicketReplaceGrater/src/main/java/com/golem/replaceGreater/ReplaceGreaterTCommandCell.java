package com.golem.replaceGreater;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.ArrayList;
import java.util.List;

public class ReplaceGreaterTCommandCell extends AbstractTicketCommand {
    private Ticket ticket;
    private String key;
    private boolean change = false;
    public ReplaceGreaterTCommandCell() {

    }

    @Override
    public void activate() {
        List<String> answer = new ArrayList<>();
        if (manager.getTicketMap().get(key).compareTo(ticket) > 0 && change) {
            manager.add(key, ticket);
            answer.add("Element was replaced.");
        }
        answer.add("Collection was successfully updated.");
        setAnswer(answer);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        key = signature.get(0).split(" ")[1];
        if (manager.checkKey(key)) {
            setAnswer(List.of("Element with this key is not exists."));
            return this;
        }
        change = true;
        ticket = new Ticket();
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
            venue.setName(signature.get(7));
            venue.setCapacity(Long.parseLong(signature.get(8))); //v cap
            venue.setType(Venue.VenueType.valueOf(signature.get(9))); //v type
            Address address = new Address();
            address.setStreet(signature.get(10)); // add
            venue.setAddress(address);
            ticket.setVenue(venue);
        }
        return this;
    }
}
