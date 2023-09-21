package com.golem.database;

import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.database.sqlScripts.DataBase;
import com.golem.informer.Informer;
import com.golem.informer.Level;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;
import com.golem.ticketCell.exception.NotAddedTException;
import com.golem.ticketCell.exception.NotUpdatedTException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseManager extends AbstractAccess {
    private Connection connection;
    private Properties properties;
    private final String url = "jdbc:postgresql://" +
            System.getProperty("dbHost","localhost") + ":" +
            System.getProperty("dbPort","5432") + "/" +
            System.getProperty("dbName","test1");
    private final String user = System.getProperty("dbUser", "postgres");
    private final String password = System.getProperty("dbPassword","pgAdmin");

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
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        properties.setProperty("stringtype", "unspecified");
        System.out.println("url: " + url);
        System.out.println("user: " + user);
        System.out.println("password: " + password);
        try {
            connection = DriverManager.getConnection(url, properties);
            connection.setAutoCommit(false);
            Informer.log(Level.INFO, "Connected");
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
            ResultSet set = connection.createStatement().executeQuery(DataBase.check);
            set.next();
            if (!set.getBoolean(1)) {
                for (int i = 0; i < DataBase.cdb.length; i++) {
                    connection
                            .createStatement()
                            .execute(DataBase.cdb[i]);
                }
                connection.commit();
                Informer.log(Level.INFO, "Database created in current system...");
            }
            else {
                Informer.log(Level.INFO, "Database already exists in current system...");
            }
        }
        catch (Exception e) {
            Informer.log(Level.INFO, e);
        }
        try {
            ResultSet set = connection
                    .createStatement()
                    .executeQuery("select _creation from collection_props where id = 1");
            set.next();
            collection.setCreationDate(set.getDate(1).toLocalDate());
            set = connection.createStatement().executeQuery("select * from tickets");
            connection.commit();
            while (set.next()) {
                ticket.setId(set.getInt(1));
                ticket.setName(set.getString(3));
                ticket.setCoordinates(selectCoordinates(set.getInt(4)));
                ticket.setCreationDate(set.getDate(5).toLocalDate());
                ticket.setPrice(set.getDouble(6));
                ticket.setComment(set.getString(7));
                ticket.setType(Ticket.TicketType.valueOf(set.getString(8)));
                ticket.setVenue(selectVenue(set.getInt(9)));
                ticket.setOwner(set.getString(10));collection.getCollection().put(set.getString(2), ticket);
                ticket = new Ticket();
            }Informer.log(Level.INFO, "Collection restored from database");
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
    public void add(String key, Ticket ticket, String login) throws NotAddedTException {
        w.lock();
        try {
            insertTickets(key, ticket, login);
            ticket.setId(selectTicketID());
            ticket.getVenue().setId(selectVenuesID());
            getCollection().getCollection().put(key, ticket);
        } catch (SQLException e) {
            Informer.log(Level.ERROR, e);
            throw new NotAddedTException(e);
        } finally {
            w.unlock();
        }
    }

    @Override
    public void update(Ticket ticket, String login) throws NotUpdatedTException {
        w.lock();
        try {
            String key = getTicketCollection().getCollection().keySet()
                    .stream()
                    .filter(x -> getCollection().getCollection().get(x).getId() == ticket.getId())
                    .findFirst()
                    .orElse(null);
            if (key == null) {
                throw new NotUpdatedTException();
            }
            del(key);
            System.out.println(">> " + key);
            add(key, ticket, login);
        } catch (NotAddedTException e) {
            Informer.log(Level.ERROR, e);
            throw new NotUpdatedTException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            w.unlock();
        }
    }

    @Override
    public void delete(String key) {
        w.lock();
        try {
            del(key);
            connection.commit();
            getCollection().getCollection().remove(key);
        }
        catch (Exception e) {
            try{
                connection.rollback();
            }
            catch (SQLException e2) {
                e.addSuppressed(e2);
            }
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
    }
    private void del (String key) throws SQLException {
        connection
                .createStatement()
                .executeUpdate("delete from tickets where _key = '" + key + "'");
    }

    @Override
    public void delete(Ticket ticket) {
        w.lock();
        try {
            connection.createStatement()
                    .executeUpdate("delete from tickets where id = '" + ticket.getId() +"'");
            connection.commit();
        }
        catch (Exception e) {
            try{
                connection.rollback();
            }
            catch (SQLException e2) {
                e.addSuppressed(e2);
            }
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public boolean checkID(int ID, String owner) {
        r.lock();
        try {
            ResultSet set = connection
                    .createStatement()
                    .executeQuery("select id from tickets where id = '" + ID + "'");
            set.next();
            return getTicketCollection(owner).getCollection()
                    .values()
                    .stream()
                    .anyMatch(x -> x.getId()==ID) && (set.getInt(1) == ID);
        } catch (Exception e) {
            Informer.log(Level.ERROR, e);
            return false;
        } finally {
            r.unlock();
        }
    }

    @Override
    public boolean checkKey(String key, String owner) {
        r.lock();
        try {
            List<String> ids = new ArrayList<>(getCollection().getCollection().keySet());
            for (String in : ids) {
                if (in.equals(key) && getCollection().getCollection().get(in).getOwner().equals(owner)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            r.unlock();
        }
    }

    @Override
    public void clear() {
        w.lock();
        try {
            connection.createStatement().execute("delete from public.tickets");
            connection.commit();
            getCollection().getCollection().clear();
        }
        catch (Exception e) {
            try{
                connection.rollback();
            }
            catch (SQLException e2) {
                e.addSuppressed(e2);
            }
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
    }

    public void clear (String owner) {
        w.lock();
        try {
            ResultSet set = connection.createStatement()
                    .executeQuery("select id, _coordinate_id, _venue_id from tickets where _owner = '" + owner + "'");
            while (set.next()) {
                connection.createStatement()
                        .executeUpdate("delete from tickets where id = '" + set.getInt(1) + "'");
            }
            connection.commit();

            List<String> list = getCollection()
                    .getCollection()
                    .keySet()
                    .stream()
                    .filter(x -> getCollection().getCollection().get(x).getOwner().equals(owner))
                    .toList();
            list.forEach(x -> getCollection().getCollection().remove(x));
        }
        catch (Exception e) {
            try{
                connection.rollback();
            }
            catch (SQLException e2) {
                e.addSuppressed(e2);
            }
            Informer.log(Level.ERROR, e);
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public TicketCollection getTicketCollection(String login) {
        r.lock();
        try {
            TicketCollection t_collection = new TicketCollection();
            t_collection.setCreationDate(this.getCollection().getCreationDate());
            this.getCollection().getCollection().keySet().stream()
                    .filter(x -> getCollection().getCollection().get(x).getOwner().equals(login))
                    .forEach(x -> t_collection.getCollection().put(x, getCollection().getCollection().get(x)));
            return t_collection;
        } catch (Exception e) {
            Informer.log(Level.ERROR, e);
            return null;
        } finally {
            r.unlock();
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
        venue.setType(Venue.VenueType.valueOf(set.getString(4)));
        venue.setAddress(selectAddress(set.getInt(5)));
        return venue;
    }

    protected void insertTickets (String key, Ticket ticket, String owner) throws SQLException {
        try {
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
            connection.commit();
        }
        catch (SQLException e) {
            connection.rollback();
            throw e;
        }


    }
    protected int selectTicketID () throws SQLException {
        ResultSet set = connection
                .createStatement()
                .executeQuery("select currval ('tickets_id_seq')");
        set.next();
        return set.getInt(1);
    }
}
