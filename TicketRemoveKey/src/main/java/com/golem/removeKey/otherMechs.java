package com.golem.removeKey;

import com.golem.ticketCell.collection.TicketCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class otherMechs {
    public static boolean checkKeyExists (TicketCollection collection, String key) {
        List<String> ids = new ArrayList<>(collection.getCollection().keySet());
        for (String in : ids) {
            if (in.equals(key)) {
                return true;
            }
        }
        return false;
    }

}
