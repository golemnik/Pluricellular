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
            return connection.createStatement()
                    .executeQuery("select currval (id) from tickets")
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
        PreparedStatement statement = connection
                .prepareStatement("insert into coordinates (_x, _y) values (?, ?)");
        statement.setLong(1, coordinates.getX());
        statement.setLong(2, coordinates.getY());
        statement.execute();
    }

    protected int selectCoordinatesID (Coordinates coordinates, boolean last) throws SQLException {
        if (last) {
            return connection
                    .createStatement()
                    .executeQuery("select currval (id) from coordinates;")
                    .getInt(1);
        }
        PreparedStatement statement = connection.prepareStatement("select id from coordinates where _x = ? and _y = ?");
        statement.setLong(1, coordinates.getX());
        statement.setLong(2, coordinates.getY());
        return statement.executeQuery().getInt(1);
    }

    protected void insertAddresses (Address address) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("insert into addresses (_street) values (?)");
        statement.setString(1, address.getStreet());
        statement.execute();
    }

    protected int selectAddressesID (Address address, boolean last) throws SQLException {
        if (last) {
            return connection
                    .createStatement()
                    .executeQuery("select currval (id) from addresses;")
                    .getInt(1);
        }
        PreparedStatement statement = connection.prepareStatement("select id from addresses where _street = ?");
        statement.setString(1, address.getStreet());
        return statement.executeQuery().getInt(1);
    }

    protected void insertVenues (Venue venue) throws SQLException {
        insertAddresses(venue.getAddress());
        PreparedStatement statement = connection
                .prepareStatement("insert into venues (_name, _capacity, _type, _address_id) values (?,?,?,?)");
        statement.setString(1, venue.getName());
        statement.setLong(2, venue.getCapacity());
        statement.setObject(3, venue.getType());
        statement.setInt(4, selectAddressesID(venue.getAddress(), true));
        statement.execute();
    }

    protected int selectVenuesID () throws SQLException {
        return connection.createStatement().executeQuery("select currval (id) from venues").getInt(1);
    }

    protected void insertTickets (Ticket ticket, int client_id) throws SQLException {
        insertCoordinates(ticket.getCoordinates());
        insertVenues(ticket.getVenue());

        PreparedStatement statement = connection
                .prepareStatement("insert into tickets (" +
                        "_name, " +
                        "_coordinate_id, " +
                        "_creationdate, " +
                        "_price, " +
                        "_comment, " +
                        "_type, " +
                        "_venue_id, " +
                        "_owner) values (")


        String sql1 = "insert into tickets (_name, _coordinate_id, _creationdate, _price, _comment, _type, _venue_id, _owner) values (";
        String sql2 =
                sqlArg(ticket.getName()) +
                sqlArg(String.valueOf(selectCoordinatesID(ticket.getCoordinates(), true))) +
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