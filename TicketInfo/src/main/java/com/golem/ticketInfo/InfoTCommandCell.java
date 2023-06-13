package com.golem.ticketInfo;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.ArrayList;
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
            setAnswer(List.of("Collection info status:",
                    String.valueOf(collection.getCollection().size()),
                    collection.getCreationDate().toString()
            ));
            return;
        }
        setAnswer(List.of("Collection is not exist."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
