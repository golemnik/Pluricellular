package com.golem.serverCell.clients.infoCom;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.serverCell.clients.Clients;

import java.util.ArrayList;
import java.util.List;

public class InfoCCommandCell extends AbstractCommand implements Cell {
    private Clients clients;

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public InfoCCommandCell(){}

    @Override
    public void activate() {
        List<String> answer = new ArrayList<>();
        answer.add("Registered clients:");
        for (String s : clients.getClients().keySet()) {
            answer.add("\""
                    + CellPrinter.Colorist.CYAN(s)
                    + "\" | "
                    + clients.getClients().get(s).getStatus());
        }
        setAnswer(answer);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
