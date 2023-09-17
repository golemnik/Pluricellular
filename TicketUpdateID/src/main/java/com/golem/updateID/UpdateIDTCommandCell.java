package com.golem.updateID;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.List;

public class UpdateIDTCommandCell extends AbstractTicketCommand {
    private List<String> signature;

    @Override
    public void activate() {
        if (!manager.checkID(Integer.parseInt(signature.get(0).split(" ")[1]), getLogin())) {
        setAnswer(List.of("Element with this id is not exists."));
        return;
        }

        Ticket ticket = new Ticket();
        ticket.setOwner(getLogin());
        ticket.setId(Integer.parseInt(signature.get(0).split(" ")[1]));
        manager.getTicketMap().put(String.valueOf(ticket.getId()), ticket);
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
            venue.setId(Integer.parseInt(signature.get(0).split(" ")[1]));
            venue.setName(signature.get(7));
            venue.setCapacity(Long.parseLong(signature.get(8))); //v cap
            venue.setType(Venue.VenueType.valueOf(signature.get(9))); //v type
            Address address = new Address();
            address.setStreet(signature.get(10)); // add
            venue.setAddress(address);
            ticket.setVenue(venue);
        }
        setAnswer(List.of("Element successfully updated."));
        if (ticket != null) {
            CellPrinter.setMessage(ticket.toReadString());
        }
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        this.signature = signature;
        return this;
    }
}
