package com.golem.ticketCell.collection.ticket;


import com.golem.ticketCell.collection.ConsoleRead;

public class Coordinates implements Comparable <Coordinates>, ConsoleRead {
    private Long x; //Поле не может быть null

    public void setX(Long x) {
        this.x = x;
    }

    public Long getX() {
        return x;
    }

    private Long y; //Максимальное значение поля: 990, Поле не может быть null

    public void setY(Long y) {
        this.y = y;
    }

    public Long getY() {
        return y;
    }
    public Coordinates () {

    }
    public Coordinates (boolean init) {
        if (init) testLoad();
    }

    public void testLoad() {
        x = 0L;
        y = 0L;
    }

    @Override
    public int compareTo(Coordinates o) {
        return (int) (Math.sqrt(Math.pow(getX().doubleValue(), 2) + Math.pow(getY().doubleValue(), 2)) -
                Math.sqrt(Math.pow(o.getX().doubleValue(), 2) + Math.pow(o.getY().doubleValue(), 2)));
    }

    @Override
    public String toReadString() {
        return "Coordinates >>\n" +
                "   X: " + x + "\n"+
                "   Y: " + y;
    }
}