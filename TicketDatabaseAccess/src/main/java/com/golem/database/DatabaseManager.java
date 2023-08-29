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
import java.time.LocalDate;
import java.util.Collections;
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
        initDataload();
    }

    private boolean init () {
        properties = new Properties();
        properties.setProperty("ssl", "false");
        properties.setProperty("user", test_user);
        properties.setProperty("password", test_password);
        properties.setProperty("stringtype", "unspecified");
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
        w.lock();
        TicketCollection collection = TicketCollection.getInstance();
        Ticket ticket = new Ticket();

        try {
            ResultSet set = connection
                    .createStatement()
                    .executeQuery("select _creation from collection_props where id = 1");
            set.next();
            collection.setCreationDate(set.getDate(1).toLocalDate());
            set = connection.createStatement().executeQuery("select * from tickets");
            while (set.next()) {
                ticket.setId(set.getInt(1));
                ticket.setName(set.getString(3));
                ticket.setCoordinates(selectCoordinates(set.getInt(4)));
                ticket.setCreationDate(set.getDate(5).toLocalDate());
                ticket.setPrice(set.getDouble(6));
                ticket.setComment(set.getString(7));
                ticket.setType(set.getObject(8, Ticket.TicketType.class));
                ticket.setVenue(selectVenue(set.getInt(9)));
                collection.getCollection().put(set.getString(2), ticket);
            }
            Informer.log(Level.INFO, "Collection restored from database");
        }
        catch (Exception e) {
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
        return true;
    }

    @Override
    public void add(String key, Ticket ticket, String login) {
        w.lock();
        try {
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
        w.lock();
        try {
            ResultSet set = connection
                    .createStatement()
                    .executeQuery("select _coordinate_id, _venue_id from tickets where _key = " + key);
            set.next();
            int c_id = set.getInt(1);
            int v_id = set.getInt(2);
            connection
                    .createStatement()
                    .executeUpdate("delete from tickets where _key = " + key +";" +
                            "delete from coordinates where id = " + c_id +";" +
                            "delete from venues where id = " + v_id +";");
        }
        catch (Exception e) {
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        w.lock();
        try {
            ResultSet set = connection
                    .createStatement()
                    .executeQuery("select _coordinate_id, _venue_id from tickets where id = " + ticket.getId());
            set.next();
            int c_id = set.getInt(1);
            int v_id = set.getInt(2);
            connection
                    .createStatement()
                    .executeUpdate("delete from tickets where id = " + ticket.getId() +";" +
                            "delete from coordinates where id = " + c_id +";" +
                            "delete from venues where id = " + v_id +";");
        }
        catch (Exception e) {
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
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
            connection.createStatement().execute("delete from tickets");
            connection.createStatement().execute("delete from venues");
            connection.createStatement().execute("delete from coordinates");
            connection.createStatement().execute("delete from addresses");
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
                    .prepareStatement("select * from tickets where _owner = '" + login + "'");
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
                .prepareStatement("insert into coordinates (_x, _y) values (?, ?)");
        statement.setLong(1, coordinates.getX());
        statement.setLong(2, coordinates.getY());
        statement.execute();
    }

    protected int selectCoordinatesID () throws SQLException {
        ResultSet set = connection
                .createStatement()
                .executeQuery("select currval ('coordinates_id_seq')");
        set.next();
        return set.getInt(1);
    }

    protected Coordinates selectCoordinates (int id) throws SQLException {
        Coordinates coordinates = new Coordinates();
        ResultSet set = connection
                .createStatement()
                .executeQuery("select * from coordinates where id = " + id);
        set.next();
        coordinates.setX(set.getLong(2));
        coordinates.setY(set.getLong(3));
        return coordinates;
    }

    protected void insertAddresses (Address address) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("insert into addresses (_street) values (?)");
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

    protected Address selectAddress (int id) throws SQLException {
        Address address = new Address();
        ResultSet set = connection
                .createStatement()
                .executeQuery("select * from addresses where id = " + id);
        set.next();
        address.setStreet(set.getString(2));
        return address;
    }

    protected void insertVenues (Venue venue) throws SQLException {
        insertAddresses(venue.getAddress());
        PreparedStatement statement = connection
                .prepareStatement("insert into venues (_name, _capacity, _type, _address_id) values (?,?,?,?)");
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

    protected Venue selectVenue (int id) throws SQLException {
        Venue venue = new Venue();
        ResultSet set = connection
                .createStatement()
                .executeQuery("select * from venues where id = " + id);
        set.next();
        venue.setId(id);
        venue.setName(set.getString(2));
        venue.setCapacity(set.getLong(3));
        venue.setType(set.getObject(4, Venue.VenueType.class));
        venue.setAddress(selectAddress(set.getInt(5)));
        return venue;
    }

    protected void insertTickets (String key, Ticket ticket, String owner) throws SQLException {
        insertCoordinates(ticket.getCoordinates());
        insertVenues(ticket.getVenue());

        PreparedStatement statement = connection
                .prepareStatement("insert into tickets (" +
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
