package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractAccess implements CollectionAccess{
    public final int priority;
    private final TicketCollection collection = new TicketCollection();

    public AbstractAccess () {
        this.priority = 1;
    }

    public AbstractAccess (int priority) {
        this.priority = priority;
    }

    @Override
    public Ticket get(String key) {
        return collection.getCollection().get(key);
    }

    @Override
    public void add(String key, Ticket ticket) {
        collection.getCollection().put(key, ticket);
    }

    @Override
    public void delete(String key) {
        collection.getCollection().remove(key);
    }

    @Override
    public void delete(Ticket ticket) {
        collection.getCollection()
                .values()
                .remove(ticket);
    }

    @Override
    public boolean checkID(int ID) {
        if (collection.getCollection().get(String.valueOf(ID)) != null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public int newID() {
        List<Integer> ids = new ArrayList<>();
        boolean useId = true;
        collection.getCollection().values().forEach(x -> ids.add(x.getId()));
        collection.getCollection().values().forEach(x -> ids.add(x.getVenue().getId()));
        ids.removeIf(Objects::isNull);
        int i = 1;
        System.out.println(ids);
        if (ids.size() == 0) {
            return i;
        }
        while (true) {
            for (Integer in : ids) {
                if (in == i) {
                    useId = false;
                    break;
                }
            }
            if (useId) {
                return i;
            }
            else {
                useId = true;
            }
            i++;
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
        collection.getCollection().clear();
    }

    @Override
    public TicketCollection getTicketCollection() {
        return collection;
    }

    @Override
    public LinkedHashMap<String, Ticket> getTicketMap() {
        return collection.getCollection();
    }

    static class AccessComparator implements Comparator<AbstractAccess> {
        @Override
        public int compare(AbstractAccess o1, AbstractAccess o2) {
            return o1.priority - o2.priority;
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
