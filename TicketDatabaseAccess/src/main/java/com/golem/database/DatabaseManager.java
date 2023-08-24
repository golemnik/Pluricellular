package com.golem.database;

import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.informer.Informer;
import com.golem.informer.Level;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.sql.*;
import java.util.Properties;

public class DatabaseManager extends AbstractAccess {
    private Connection connection;
    private Properties properties;

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
    public void add(String key, Ticket ticket, String login) {
        w.lock();
        try {
            ticket.setId(newID());
            ticket.getVenue().setId(newID());
            insertTickets(ticket, selectClientsID());
            getCollection().getCollection().put(key, ticket);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            w.unlock();
        }
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
    protected int newID() {
        try {
            return statement
                    .executeQuery("select currval (id) from tickets;")
                    .getInt(0);
        }
        catch (Exception e) {
            Informer.log(Level.ERROR, e);
        }
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public TicketCollection getTicketCollection() {
        return null;
    }

    protected void insertCoordinates (Coordinates coordinates) throws SQLException {
        String sql = "insert into coordinates (_x, _y) values (" +
                sqlArg(coordinates.getX().toString()) +
                quotes(coordinates.getY().toString()) + ");";
        statement.executeUpdate(sql);
    }

    protected int selectCoordinatesID () throws SQLException {
        return statement.executeQuery("select currval (id) from coordinates;").getInt(0);
    }

    protected void insertAddresses (Address address) throws SQLException {
        String sql = "insert into addresses (_street) values (" +
                quotes(address.getStreet()) + "); ";
        statement.executeUpdate(sql);
    }

    protected int selectAddressesID () throws SQLException {
        return statement.executeQuery("select currval (id) from addresses;").getInt(0);
    }

    protected void insertVenues (Venue venue) throws SQLException {
        insertAddresses(venue.getAddress());
        String sql = "insert into venues (_name, _capacity, _type, _address_id) values (" +
                sqlArg(venue.getName()) +
                sqlArg(venue.getCapacity().toString())+
                sqlArg(venue.getType().toString()) +
                quotes(String.valueOf(selectAddressesID())) + ");";
        statement.executeUpdate(sql);
    }

    protected int selectVenuesID () throws SQLException {
        return statement.executeQuery("select currval (id) from venues;").getInt(0);
    }

    protected void insertTickets (Ticket ticket, int client_id) throws SQLException {
        insertCoordinates(ticket.getCoordinates());
        insertVenues(ticket.getVenue());
        String sql1 = "insert into tickets (_name, _coordinate_id, _creationdate, _price, _comment, _type, _venue_id, _client_id) values (";
        String sql2 =
                sqlArg(ticket.getName()) +
                sqlArg(String.valueOf(selectCoordinatesID())) +
                sqlArg(ticket.getCreationDate().toString()) +
                sqlArg(ticket.getPrice().toString()) +
                sqlArg(ticket.getComment()) +
                sqlArg(ticket.getType().toString()) +
                sqlArg(String.valueOf(selectCoordinatesID())) +
                ");";
        statement.executeUpdate(sql1+sql2);
    }

    protected String quotes(String str) {return "'" + str + "'";}
    protected String comma (String str) {return  str + ", ";}
    protected String sqlArg(String str) {return comma(quotes(str));}
}


// jdbc:postgresql://localhost:5432/cellar
// tester
// 12345