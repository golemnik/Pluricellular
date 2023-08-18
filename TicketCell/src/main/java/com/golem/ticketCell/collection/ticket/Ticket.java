package com.golem.ticketCell.collection.ticket;

import com.golem.ticketCell.collection.ConsoleRead;

import java.time.LocalDate;

/**
 * Ticket element is main data object for this collection
 */

public class Ticket implements Comparable <Ticket>, ConsoleRead {
    /**
     * unique id.
     * -not null
     * -autogenerated
     * -greater than 0
     */
    private int id;

    /**
     * Ticket name
     * -not null @see
     * -not empty @see
     */
    private String name = "";

    /**
     * coordinates
     * -not null
     */
    private Coordinates coordinates;

    /**
     * Creation date
     * -not null
     * -autogenerated
     */
    private LocalDate creationDate;

    /**
     * Ticket price
     * -not null
     * -greater than 0
     */
    private Double price;

    /**
     * ticket comment
     * -can be null
     */
    private String comment;

    /**
     * Ticket type
     * -not null
     */
    private TicketType type;

    /**
     * ticket venue
     * -can be null
     */
    private Venue venue; //Поле может быть null

    /**
     * id setter
     * @param id - int value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * id getter
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * name setter
     * @param name - ticket name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * name getter
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * coordinate setter
     * @param coordinates - ticket coordinate
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     *  coordinate getter
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     *  creation date setter
     * @param creationDate - ticket creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * creation date setter
     * @return LocalDate
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * price setter
     * @param price - ticket price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * price getter
     * @return Double
     */
    public Double getPrice() {
        return price;
    }

    /**
     * comment setter
     * @param comment - ticket commentary
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * ticket getter
     * @return String
     */
    public String getComment() {
        return comment;
}

    /**
     * ticket typesetter
     * @param type - ticket type
     */
    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * ticket type getter
     * @return TicketType
     */
    public TicketType getType() {
        return type;
    }

    /**
     * venue setter
     * @param venue - ticket venue
     */
    public void setVenue (Venue venue) {
        this.venue = venue;
    }

    /**
     * venue getter
     * @return Venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     *  initialization for not null objects venue and coordinates
     */
    {
        coordinates = new Coordinates();
        venue = new Venue();
    }

    /**
     * default constructor. Just init object.
     */
    public Ticket () {}

    /**
     * constructor with init argument. Can add unchangeable data for tests.
     * @param init - boolean for test init
     */
    public Ticket (boolean init) {
        if (init) this.testLoad();
    }

    /**
     * unchangeable data. Just for test, not for work.
     */
    public void testLoad() {
        id = -1;
        name = "unnamed";
        coordinates = new Coordinates(true);
        creationDate = LocalDate.now();
        price = 0.0;
        comment = "no comments";
        type = TicketType.CHEAP;
        venue = new Venue(true);
    }

    /**
     * implemented and overrided compareTo method.
     *
     * @param o ticket to be compared.
     * @return int
     * -less than 0 -> o less than object
     * -equal to 0 -> 0 equal to object (due id generation - imposable)
     * -greater than 0 -> greater than object
     */
    @Override
    public int compareTo(Ticket o) {
        int compare;
        if ((compare = getName().compareTo(o.getName())) != 0) return compare;
        if ((compare = getCoordinates().compareTo(o.getCoordinates())) != 0) return compare;
        if ((compare = getPrice().compareTo(o.getPrice())) != 0) return compare;
        if ((compare = getComment().compareTo(o.getComment())) != 0) return compare;
        if ((compare = getType().compareTo(o.getType())) != 0) return compare;
        if (this.getVenue() != null && o.getVenue() == null) return 1;
        if (this.getVenue() == null && o.getVenue() != null) return -1;
        if ((compare = getVenue().compareTo(o.getVenue())) != 0) return compare;
        if ((compare = getCreationDate().compareTo(o.getCreationDate())) != 0) return compare;
        return o.getId() - getId();
    }

    /**
     * form string to console output. Comfortable for user reading.
     * @return String
     */
    @Override
    public String toReadString() {
        String v = "";
        if (venue != null) {
            v += "\n";
            v += venue.toReadString();
        }
        return "Ticket >>\n" +
                "   id: " + id + "\n"+
                "   name: " + name + "\n"+
                "   creationDate: " + creationDate + "\n"+
                "   price: " + price + "\n"+
                "   comment: " + comment + "\n"+
                "   type: " + type.toString() + "\n"+
                coordinates.toReadString() +
                v;
    }

    /**
     * enum for ticket type. Used generally in pair with ticket.
     */
    public enum TicketType {
        VIP,
        USUAL,
        BUDGETARY,
        CHEAP
    }

}