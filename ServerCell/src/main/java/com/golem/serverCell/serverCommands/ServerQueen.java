package com.golem.serverCell.serverCommands;

import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.serverCell.clients.Clients;

import java.util.List;

public class ServerQueen extends AbstractQueenCell {
    @Override
    public void activate() {

    }

    @Override
    public List<AbstractSCellFactory> extractFactories(ModuleLayer layer) {
        Clients clients = Clients.getInstance();
        List<AbstractSCellFactory> list = AbstractSCellFactory.getTicketCellFactories(layer);
        list.forEach(x -> x.addClients(clients));
        return list;
    }
}
