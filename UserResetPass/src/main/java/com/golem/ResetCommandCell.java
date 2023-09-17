package com.golem;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.innerMechanics.Crypt;
import com.golem.serverCell.clients.Clients;
import com.golem.serverCell.clients.RegClient;

import java.util.List;

public class ResetCommandCell extends AbstractCommand implements SystemCommand {
    private Clients clients;
    private String login = ".";
    private String new_password = ".";
    private String r_new_password = ".";

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    @Override
    public void activate() {
        if (!Crypt.encrypt(new_password).equals(Crypt.encrypt(r_new_password))) {
            setAnswer(List.of("New password and repetition " +
                    CellPrinter.Colorist.RED("doesn't") +
                    " match."));
            return;
        }
        if (getLogin() == null || !clients.existClient(login) || !getLogin().equals(login)) {
            setAnswer(List.of("Reset password for " +
                    CellPrinter.Colorist.RED("incorrect") +
                    " user."));
            return;
        }
        clients.getClients().remove(login);
        clients.getClients().put(login, new RegClient(Crypt.encrypt(new_password)));
        setAnswer(List.of("Reset password " +
                CellPrinter.Colorist.GREEN("successfully") +
                " done in system.", "Please, re-login to update the password on client side."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        login = signature.get(0).split(" ")[1];
        new_password = signature.get(0).split(" ")[2];
        r_new_password = signature.get(0).split(" ")[3];
        return this;
    }
}
