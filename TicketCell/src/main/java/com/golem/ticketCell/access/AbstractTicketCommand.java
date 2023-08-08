package com.golem.ticketCell.access;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;

public abstract class AbstractTicketCommand extends AbstractCommand {
    protected AbstractAccess manager;

    public void setManager(AbstractAccess manager) {
        this.manager = manager;
    }
}
