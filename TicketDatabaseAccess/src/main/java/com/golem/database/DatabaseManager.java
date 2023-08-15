package com.golem.database;

import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseManager extends AbstractAccess {
    private Connection connection;
    private Properties properties;

    private final String url = "jdbc:postgresql://pg/studs";
    private final String test_url = "jdbc:postgresql://pg/studs";
    private final String user = "368324";
    private final String password = "secret";

    {
        init();
    }

    public DatabaseManager () {

    }

    private boolean init () {
        properties.setProperty("ssl", "false");
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        try {
            connection = DriverManager.getConnection(test_url, properties);
            return true;
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
            return false;
        }
    }

    @Override
    public Ticket get(String key) {
        return null;
    }

    @Override
    public void add(String key, Ticket ticket) {

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void delete(Ticket ticket) {

    }

    @Override
    public boolean checkID(int ID) {
        return false;
    }

    @Override
    public boolean checkKey(String key) {
        return false;
    }

    @Override
    public int newID() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public TicketCollection getTicketCollection() {
        return null;
    }
}


// jdbc:postgresql://localhost:5432/cellar
// tester
// 12345