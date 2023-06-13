package com.golem.ticketInfo;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.List;

public class InfoTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    public InfoTCommandCell() {

    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        if (collection != null) {
            collection.getCollection().clear();
            setAnswer(List.of(collection.toReadString()));
            return;
        }
        setAnswer(List.of("Collection is not exist."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
