package com.golem.ticketCell.collection.ticket;


import com.golem.ticketCell.collection.ConsoleRead;

public class Address implements Comparable <Address>, ConsoleRead {

    /**
     * Address where to go with ticket
     * -can be null
     */
    private String street;

    /**
     * street setter
     * @param street - address street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * street getter
     * @return String
     */
    public String getStreet() {
        return street;
    }

    /**
     * default constructor. Just init object.
     */
    public Address () {}

    /**
     * constructor with init argument. Can add unchangeable data for tests.
     * @param init - boolean for test init
     */
    public Address (boolean init) {
        if (init) this.testLoad();
    }

    /**
     * called from constructor, add test value
     */
    public void testLoad() {
       street = "unknown street";
    }

    /**
     *
     * @param o the adress to be compared by strings
     * @return int
     * -less than 0 -> o less than object
     * -equal to 0 -> 0 equal to object (due id generation - imposable)
     * -greater than 0 -> greater than object
     */
    @Override
    public int compareTo(Address o) {
        return getStreet().compareTo(o.getStreet());
    }

    /**
     * form string to console output. Comfortable for user reading.
     * @return String
     */
    @Override
    public String toReadString() {
        return "Address >>\n" +
                "   street: " + street;
    }
}