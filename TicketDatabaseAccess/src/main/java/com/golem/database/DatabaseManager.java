package com.golem.database;

import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager extends AbstractAccess {
    private Connection connection;
    private Properties properties;
    private Statement statement;

    private final String url = "jdbc:postgresql://pg/studs";
    private final String user = "368324";
    private final String password = "secret";

    private final String test_url = "jdbc:postgresql://localhost:5432/postgres";
    private final String test_user = "postgres";
    private final String test_password = "pgAdmin";

    {
        init();
    }

    public DatabaseManager () {
        super(10);
    }

    private boolean init () {
        properties = new Properties();
        properties.setProperty("ssl", "false");
        properties.setProperty("user", test_user);
        properties.setProperty("password", test_password);

        try {
            connection = DriverManager.getConnection(test_url, properties);
            statement = connection.createStatement();

            System.out.println("Connected");
            return true;
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
            return false;
        }
    }

    private boolean initDataload () {
        return true;
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

    private static int lastID = 0;
    @Override
    public int newID() {
        return generateID();
    }

    protected int generateID () {

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