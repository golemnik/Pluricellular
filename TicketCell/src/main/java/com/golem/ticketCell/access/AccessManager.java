package com.golem.ticketCell.access;

import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AccessManager extends AbstractAccess {

    @Override
    public void add(String key, Ticket ticket, String login) {
        w.lock();
        try {
            ticket.setId(newID());
            ticket.getVenue().setId(newID());
            getCollection().getCollection().put(key, ticket);
        }
        finally {
            w.unlock();
        }

    }

    @Override
    public void delete(String key) {
        w.lock();
        try {
            getCollection().getCollection().remove(key);
        }
        finally {
            w.unlock();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        w.lock();
        try {
            getCollection().getCollection()
                    .values()
                    .remove(ticket);
        }
        finally {
            w.unlock();
        }
    }
    private static int lastID = 1;
    private int newID() {
        List<Integer> ids = new ArrayList<>();
        boolean useId = true;
        getCollection().getCollection().values().forEach(x -> ids.add(x.getId()));
        getCollection().getCollection().values().forEach(x -> ids.add(x.getVenue().getId()));
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


}
