package com.golem.replaceGreater;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReplaceGreaterTCommandCell extends AbstractTicketCommand {
    private List<String> signature;

    @Override
    public void activate() {
        String key = signature.get(0).split(" ")[1];
        if (!manager.checkKey(key, getLogin())) {
            setAnswer(List.of("Element with this key is not exists."));
            return;
        }
        Ticket ticket = new Ticket();
        ticket.setOwner(getLogin());
        ticket.setCreationDate(LocalDate.now());
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
        List<String> answer = new ArrayList<>();
        try {
            Ticket old_ticket = manager.getTicketCollection().getCollection().get(key);
            ticket.setId(old_ticket.getId());
            ticket.getVenue().setId(old_ticket.getVenue().getId());
            if (old_ticket.compareTo(ticket) < 0) {
                manager.update(ticket, getLogin());
                answer.add("Element was replaced.");
            }
        }
        catch (Exception e) {
            setAnswer(List.of("Element wasn't replaced."));
            return;
        }
        answer.add("Collection was successfully updated.");
        setAnswer(answer);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        this.signature = signature;
        return this;
    }
}
