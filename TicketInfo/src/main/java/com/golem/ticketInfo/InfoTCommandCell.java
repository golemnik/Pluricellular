package com.golem.ticketInfo;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;
import java.util.List;

public class InfoTCommandCell extends AbstractTicketCommand {
    @Override
    public void activate() {
        if (manager.getTicketCollection() != null) {
            setAnswer(List.of("Collection info status:",
                    String.valueOf(manager.getTicketCollection().getCollection().size()),
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
