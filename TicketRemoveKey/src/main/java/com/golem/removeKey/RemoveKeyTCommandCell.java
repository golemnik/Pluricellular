package com.golem.removeKey;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;

import java.util.List;

public class RemoveKeyTCommandCell extends AbstractTicketCommand {
    public RemoveKeyTCommandCell() {
    }
    @Override
    public void activate() {
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        if (manager.checkKey(signature.get(0).split(" ")[1], getLogin())) {
            manager.getTicketMap().remove(signature.get(0).split(" ")[1]);
            setAnswer(List.of("Element successfully removed."));
            return this;
        }
        setAnswer(List.of("Element with this key is not exists."));
        return this;
    }
}
