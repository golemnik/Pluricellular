package com.golem.ticketCell.collection.ticket;

import com.golem.ticketCell.collection.ConsoleRead;

public class Venue implements Comparable<Venue>, ConsoleRead {
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null
    private Address address; //Поле не может быть null

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    public VenueType getType() {
        return type;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Venue () {

    }
    public Venue (boolean init) {
        if (init) this.testLoad();
    }

    public void testLoad() {
        id = -1;
        name = "unnamed Venue";
        capacity = 0L;
        type = VenueType.BAR;
        address = new Address(true);
    }

    @Override
    public int compareTo(Venue o) {
        int compare;
        if (o == null) return 1;
        if ((compare = getName().compareTo(o.getName())) != 0) return compare;
        if ((compare = getCapacity().compareTo(o.getCapacity())) != 0) return compare;
        if ((compare = getAddress().compareTo(o.getAddress())) != 0) return compare;
        if ((compare = getType().compareTo(o.getType())) != 0) return compare;
        return getId().compareTo(o.getId());
    }

    @Override
    public String toReadString() {
        String a;
        if (address != null) {
            a = address.toReadString();
        }
        else {
            a = null;
        }
        return "Venue >>\n" +
                "   id: " + id + "\n"+
                "   name: " + name + "\n"+
                "   capacity: " + capacity + "\n"+
                "   type: " + type + "\n"+
                a;
    }

    public enum VenueType {
        BAR,
        LOFT,
        OPEN_AREA,
        THEATRE,
        MALL
    }



}