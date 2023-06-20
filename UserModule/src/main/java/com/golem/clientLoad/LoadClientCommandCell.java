package com.golem.clientLoad;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class LoadClientCommandCell extends AbstractCommand {
    private final String file = "clients.json";
    private Clients clients = new Clients();

    public LoadClientCommandCell() {
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        setAnswer(List.of("loaded..."));
        return this;
    }

    @Override
    public void activate() {
        JsonParser jp;
        Clients t_clients;
        try {
            jp = new JsonParser(file);
            t_clients = jp.parseLoad();
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() +
                    "\nDue error to load from file, clients list will be loaded from system file <<clients.json>>");
            jp = new JsonParser("clients.json");
            t_clients = jp.parseLoad();
        }
        this.clients.setClients(t_clients.getClients());
    }
}
