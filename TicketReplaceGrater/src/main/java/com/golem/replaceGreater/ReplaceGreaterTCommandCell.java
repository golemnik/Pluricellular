package com.golem.replaceGreater;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.List;

public class ReplaceGreaterTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    private Ticket ticket;
    private String key;
    private boolean change = false;
    public ReplaceGreaterTCommandCell() {

    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        if (collection.getCollection().get(key).compareTo(ticket) > 0 && change) {
            collection.getCollection().put(key, ticket);
        }
        setAnswer(List.of("Collection was successfully updated."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        key = signature.get(0).split(" ")[1];
        if (!otherMechs.checkKeyExists(collection, key)) {
            setAnswer(List.of("Element with this key is not exists."));
            return this;
        }
        change = true;
        ticket = new Ticket();
        ticket.setId(otherMechs.getId(collection));
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
            venue.setId(otherMechs.getId(collection));
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
