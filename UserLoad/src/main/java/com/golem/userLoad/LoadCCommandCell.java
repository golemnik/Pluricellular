package com.golem.userLoad;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.serverCell.clients.Clients;

import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class LoadCCommandCell extends AbstractCommand {
    private final String file = "clients.json";
    private Clients clients;

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public LoadCCommandCell() {
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        setAnswer(List.of("loading clients..."));
        return this;
    }

    @Override
    public void activate() {
        JsonParser jp;
        try {
            jp = new JsonParser(file);
            clients.setClients(jp.parseLoad().getClients());
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() +
                    "\nDue error to load from file, clients list will be loaded from system file <<reserved_clients.json>>");
            jp = new JsonParser("reserved_clients.json");
            clients.setClients(jp.parseLoad().getClients());
        }
    }
}
