package com.golem.regCommand;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.innerMechanics.Crypt;
import com.golem.serverCell.clients.Clients;

import java.util.List;

public class RegCommandCell extends AbstractCommand implements Cell {
    private Clients clients;
    private String login = ".";
    private String password = ".";
    public RegCommandCell(){}

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    @Override
    public void activate() {
        if (!clients.existClient(login)) {
            clients.addUser(login, password);
            setAnswer(List.of("User " +
                    CellPrinter.Colorist.GREEN(login) +
                    " successfully registered in system."));
            return;
        }
        setAnswer(List.of("User " +
                CellPrinter.Colorist.RED(login) +
                " already registered in system."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        login = signature.get(0).split(" ")[1];
        password = signature.get(0).split(" ")[2];
//        CellPrinter.setMessage("Command: " + login + " " + password + " " + Crypt.encrypt(password));
        password = Crypt.encrypt(password);
        return this;
    }
}
