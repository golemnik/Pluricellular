package com.golem.removeKey;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.access.AbstractTicketCommand;

import java.util.List;

public class RemoveKeyTCommandCell extends AbstractTicketCommand {
    private List<String> signature;
    @Override
    public void activate() {
        String key = signature.get(0).split(" ")[1];
        if (manager.checkKey(key, getLogin())) {
            manager.delete(key);
            setAnswer(List.of("Element successfully removed."));
            return;
        }
        setAnswer(List.of("Element with this key is not exists."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        this.signature = signature;
        return this;
    }
}
