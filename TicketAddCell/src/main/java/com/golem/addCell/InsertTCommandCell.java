package com.golem.addCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;
import com.golem.ticketCell.schemas.SignatureRegex;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertTCommandCell extends AbstractTicketCommand {
    private Ticket ticket;
    private List<String> signature;
    public InsertTCommandCell() {
    }
    @Override
    public void activate() {
        Integer id = getID(signature.get(0));
        if (id == null || checkID(id)) {
            setAnswer(List.of("This id is already used. Insert failed."));
            return;
        }
        ticket = new Ticket();
        ticket.setOwner(getLogin());
        ticket.setName(signature.get(1)); // t name
        ticket.setCreationDate(LocalDate.now());
        ticket.setPrice(Double.parseDouble(signature.get(2))); // t price
        ticket.setComment(signature.get(3)); // t comment
        ticket.setType(Ticket.TicketType.valueOf(signature.get(4))); // t type
        Coordinates coord = new Coordinates();
        coord.setX(Long.parseLong(signature.get(5))); // t x
        coord.setY(Long.parseLong(signature.get(6))); // t y
        ticket.setCoordinates(coord);
        Venue venue = new Venue();
        if (signature.get(7) != null) { // v name
            venue.setName(signature.get(7));
            venue.setCapacity(Long.parseLong(signature.get(8))); //v cap
            venue.setType(Venue.VenueType.valueOf(signature.get(9))); //v type
            Address address = new Address();
            address.setStreet(signature.get(10)); // add
            venue.setAddress(address);
            ticket.setVenue(venue);
        }
        else {
            venue = null;
            ticket.setVenue(venue);
        }
        try {
            manager.add(String.valueOf(id), ticket, getLogin());
            setAnswer(List.of("Element successfully inserted."));
        }
        catch (Exception e) {
            setAnswer(List.of("Element wasn't inserted."));
        }
        CellPrinter.setMessage(getAnswer().toString());
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        this.signature = signature;
        return this;
    }

    private Integer getID (String strID) {
        Pattern pattern = Pattern.compile("^" + "insert " + SignatureRegex.ID +"?" + "$");
        Matcher m = pattern.matcher(strID);
        if (m.matches() && m.group(1) != null) {
            return Integer.valueOf(m.group(1));
        }
        return null;
    }

    private boolean checkID (Integer ID) {
        return manager.checkID(ID, getLogin());
    }
}
