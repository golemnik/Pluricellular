package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public abstract class AbstractAccess implements CollectionAccess {
    protected final ReadWriteLock rwl = new ReentrantReadWriteLock();
    protected final Lock r = rwl.readLock();
    protected final Lock w = rwl.writeLock();

    public final int priority;
    private final TicketCollection collection = TicketCollection.getInstance();

    public AbstractAccess () {
        this.priority = 1;
    }

    public AbstractAccess (int priority) {
        this.priority = priority;
    }

    @Override
    public Ticket get(String key) {
        r.lock();
        try {
            return collection.getCollection().get(key);
        }
        finally {
            r.unlock();
        }
    }

    @Override
    public void add(String key, Ticket ticket, String login) {
        w.lock();
        try {
            ticket.setId(newID());
            ticket.getVenue().setId(newID());
            collection.getCollection().put(key, ticket);
        }
        finally {
            w.unlock();
        }

    }

    @Override
    public void delete(String key) {
        w.lock();
        try {
            collection.getCollection().remove(key);
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        w.lock();
        try {
            collection.getCollection()
                    .values()
                    .remove(ticket);
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public boolean checkID(int ID) {
        w.lock();
        try {
            return collection.getCollection().get(String.valueOf(ID)) == null;
        }
        finally {
            w.unlock();
        }
    }

    private static int lastID = 1;
    protected int newID() {
        List<Integer> ids = new ArrayList<>();
        boolean useId = true;
        collection.getCollection().values().forEach(x -> ids.add(x.getId()));
        collection.getCollection().values().forEach(x -> ids.add(x.getVenue().getId()));
        ids.removeIf(Objects::isNull);
        if (ids.size() == 0) {
            return lastID++;
        }
        Collections.sort(ids);
        lastID = ids.get(ids.size() - 1);
        while (true) {
            for (Integer in : ids) {
                if (in == lastID) {
                    useId = false;
                    break;
                }
            }
            if (useId) {
                return lastID;
            }
            else {
                useId = true;
            }
            lastID++;
        }
    }

    public boolean checkKey(String key) {
        List<String> ids = new ArrayList<>(collection.getCollection().keySet());
        for (String in : ids) {
            if (in.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        w.lock();
        try {
            collection.getCollection().clear();
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public void clear(String owner) {
        clear();
    }

    @Override
    public TicketCollection getTicketCollection() {
        w.lock();
        try {
            return collection;
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public TicketCollection getTicketCollection(String login) {
        return getTicketCollection();
    }

    protected TicketCollection getCollection () {
        return collection;
    }


    @Override
    public LinkedHashMap<String, Ticket> getTicketMap() {
        w.lock();
        try {
            return collection.getCollection();
        }
        finally {
            w.unlock();
        }
    }

    static class AccessComparator implements Comparator<AbstractAccess> {
        @Override
        public int compare(AbstractAccess o1, AbstractAccess o2) {
            return o2.priority - o1.priority;
        }
    }

    public static List<AbstractAccess> getCollectionAccess(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractAccess.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .sorted(new AccessComparator())
                .collect(Collectors.toList())
                .subList(0, 1);
    }
}
