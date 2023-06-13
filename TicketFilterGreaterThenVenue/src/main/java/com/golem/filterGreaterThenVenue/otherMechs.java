package com.golem.filterGreaterThenVenue;

import com.golem.ticketCell.collection.TicketCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class otherMechs {
    public static int getId (TicketCollection collection) {
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
}
