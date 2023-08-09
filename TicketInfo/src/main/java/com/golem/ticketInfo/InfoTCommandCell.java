package com.golem.ticketInfo;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.ArrayList;
import java.util.List;

public class InfoTCommandCell extends AbstractTicketCommand {
    public InfoTCommandCell() {

    }


    @Override
    public void activate() {
        if (manager.getTicketCollection() != null) {
            setAnswer(List.of("Collection info status:",
                    String.valueOf(manager.getTicketMap().size()),
                    manager.getTicketCollection().getCreationDate().toString()
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
