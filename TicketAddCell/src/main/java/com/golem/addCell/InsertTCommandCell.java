package com.golem.addCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;
import com.golem.ticketCell.schemas.SignatureRegex;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    private Ticket ticket;
    public InsertTCommandCell() {
    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        CellPrinter.setMessage(getAnswer().toString());
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {

        Integer id = collectionID(signature.get(0));
        if (id == null) {
            setAnswer(List.of("This id is already used. Insert failed."));
            return this;
        }
        ticket = new Ticket();
        ticket.setId(id);
        collection.getCollection().put(String.valueOf(ticket.getId()), ticket);
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
//        System.out.println(">>" + signature.get(7) + "<<");
        if (signature.get(7) != null) { // v name
            venue.setId(otherMechs.getId(collection));
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
        setAnswer(List.of("Element successfully inserted."));
        return this;
    }


    private Integer collectionID (String strID) {
        Pattern pattern = Pattern.compile("^" + "insert( " + SignatureRegex.ID +")?" + "$");
        Matcher m = pattern.matcher(strID);
        if (m.matches() && m.group(2) == null) {
            return otherMechs.getId(collection);
        }
        else {
            if (otherMechs.checkID(collection, Integer.parseInt(m.group(2).trim()))) {
                return Integer.parseInt(m.group(2).trim());
            }
            return null;
        }
    }
}
