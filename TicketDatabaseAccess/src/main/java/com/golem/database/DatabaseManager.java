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

    private final String test_url = "jdbc:postgresql://localhost:5432/test1";
    private final String test_user = "postgres";
    private final String test_password = "pgAdmin";

    {
        init();
    }

    public DatabaseManager () {
        super(10);
        Informer.log(Level.INFO, "Database manager connected...");

    }

    private boolean init () {
        properties = new Properties();
        properties.setProperty("ssl", "false");
        properties.setProperty("user", test_user);
        properties.setProperty("password", test_password);
        properties.setProperty("stringtype", "unspecified");
//        properties.setProperty("options", "-c client_encoding=win866");
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
            Informer.log(Level.INFO, "DB inserting ticket");
            ticket.setId(newID());
            ticket.getVenue().setId(newID());
            insertTickets(key, ticket, login);
            getCollection().getCollection().put(key, ticket);
        } catch (SQLException e) {
            Informer.log(Level.ERROR, e);
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
            ResultSet set = connection.createStatement()
                    .executeQuery("select currval (id) from public.tickets");
            if (set.next()) {
                return set.getInt(1);
            }
        }
        catch (Exception e) {
            Informer.log(Level.ERROR, e);
        }
        return 1;
    }

    @Override
    public void clear() {
        w.lock();
        try {
            connection.createStatement().execute("delete from public.tickets");
            connection.createStatement().execute("delete from public.venues");
            connection.createStatement().execute("delete from public.coordinates");
            connection.createStatement().execute("delete from public.addresses");
        }
        catch (Exception e) {
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
    }

    public void clear (String owner) {
        w.lock();
        try {
            connection.createStatement().execute("delete from public.tickets");
            connection.createStatement().execute("delete from public.venues");
            connection.createStatement().execute("delete from public.coordinates");
            connection.createStatement().execute("delete from public.addresses");
        }
        catch (Exception e) {
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public TicketCollection getTicketCollection(String login) {
        w.lock();
        try {
            TicketCollection collection = new TicketCollection();
            PreparedStatement statement = connection
                    .prepareStatement("select * from public.tickets where _owner = '" + login + "'");
            ResultSet rs = statement.executeQuery();
            do {
                Informer.log(Level.INFO, "cycled...");
            }
            while (rs.next());


            return null;
        } catch (SQLException e) {
            Informer.log(Level.ERROR, e);
            return null;
        } finally {
            w.unlock();
        }

    }

    protected void insertCoordinates (Coordinates coordinates) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("insert into public.coordinates (_x, _y) values (?, ?)");
        statement.setLong(1, coordinates.getX());
        statement.setLong(2, coordinates.getY());
        statement.execute();
    }

    protected int selectCoordinatesID () throws SQLException {
        ResultSet set =  connection
                .createStatement()
                .executeQuery("select currval ('coordinates_id_seq')");
        set.next();
        return set.getInt(1);
    }

    protected void insertAddresses (Address address) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("insert into public.addresses (_street) values (?)");
        statement.setString(1, address.getStreet());
        statement.execute();
    }

    protected int selectAddressesID () throws SQLException {
        ResultSet set = connection
                .createStatement()
                .executeQuery("select currval ('addresses_id_seq')");
        set.next();
        return set.getInt(1);
    }

    protected void insertVenues (Venue venue) throws SQLException {
        insertAddresses(venue.getAddress());
        PreparedStatement statement = connection
                .prepareStatement("insert into public.venues (_name, _capacity, _type, _address_id) values (?,?,?,?)");
        statement.setString(1, venue.getName());
        statement.setLong(2, venue.getCapacity());
        statement.setString(3, venue.getType().toString());
        statement.setInt(4, selectAddressesID());
        statement.execute();
    }

    protected int selectVenuesID () throws SQLException {
        ResultSet set = connection
                .createStatement()
                .executeQuery("select currval ('venues_id_seq')");
        set.next();
        return set.getInt(1);
    }

    protected void insertTickets (String key, Ticket ticket, String owner) throws SQLException {
        insertCoordinates(ticket.getCoordinates());
        insertVenues(ticket.getVenue());

        PreparedStatement statement = connection
                .prepareStatement("insert into public.tickets (" +
                        "_key, " +
                        "_name, " +
                        "_coordinate_id, " +
                        "_creationdate, " +
                        "_price, " +
                        "_comment, " +
                        "_type, " +
                        "_venue_id, " +
                        "_owner" +
                        ") values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, key);
        statement.setString(2, ticket.getName());
        statement.setInt(3, selectCoordinatesID());
        statement.setObject(4, ticket.getCreationDate());
        statement.setDouble(5, ticket.getPrice());
        statement.setString(6, ticket.getComment());
        statement.setObject(7, ticket.getType().toString());
        statement.setInt(8, selectVenuesID());
        statement.setString(9, owner);
        statement.execute();
    }
}


// jdbc:postgresql://localhost:5432/cellar
// tester
// 12345