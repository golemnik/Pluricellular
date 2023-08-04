package com.golem.userSave;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.serverCell.clients.Clients;

import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class SaveCCommandCell extends AbstractCommand {
    private Clients clients;
    private final String file = "clients.json";

    public SaveCCommandCell() {
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        setAnswer(List.of("saving registered clients.."));
        return this;
    }

    @Override
    public void activate() {
        JsonParser jp;
        try {
            jp = new JsonParser(file);
            jp.parseSave(clients);
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() +
                    "\nDue error to save in file, registered clients will be saved in system file <<reserved_clients.json>>");
            jp = new JsonParser("reserved_clients.json");
            jp.parseSave(clients);
        }
    }
}
