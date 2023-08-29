package com.golem.clearCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.List;

public class ClearTCommandCell extends AbstractTicketCommand {
    public ClearTCommandCell () {

    }

    @Override
    public void activate() {
        if (manager != null) {
            manager.clear(getLogin());
            setAnswer(List.of("Collection is empty now."));
            return;
        }
        setAnswer(List.of("Collection is not exist."));

    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
