package com.golem.removeKey;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.Address;
import com.golem.ticketCell.collection.ticket.Coordinates;
import com.golem.ticketCell.collection.ticket.Ticket;
import com.golem.ticketCell.collection.ticket.Venue;

import java.util.List;

public class RemoveKeyTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    public RemoveKeyTCommandCell() {
    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        System.out.println(getAnswer());
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        if (otherMechs.checkKeyExists(collection, signature.get(0).split(" ")[1])) {
            collection.getCollection().remove(signature.get(0).split(" ")[1]);
            setAnswer(List.of("Element successfully removed."));
            return this;
        }
        setAnswer(List.of("Element with this key is not exists."));
        return this;
    }
}
