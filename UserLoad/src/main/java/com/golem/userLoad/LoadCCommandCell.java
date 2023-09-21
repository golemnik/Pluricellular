package com.golem.userLoad;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.serverCell.clients.Clients;
import com.golem.serverCell.clients.RegClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class LoadCCommandCell extends AbstractCommand {
    private Clients clients;

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    @Override
    public void activate() {
        List<String> anwser = new ArrayList<>();
        JsonParser jp;
        try {
            anwser.add("loading clients...");
            jp = new JsonParser("clients.json");
            Clients loaded_clients = jp.parseLoad();
            clients.setClients(loaded_clients.getClients());
            for (String c : loaded_clients.getClients().keySet()) {
                anwser.add(c + " | " + loaded_clients.getClients().get(c).getStatus());
            }
            setAnswer(anwser);
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() +
                    "\nDue error to load from file, clients list will be loaded from system file <<reserved_clients.json>>");
            jp = new JsonParser("reserved_clients.json");
            Clients loaded_clients = jp.parseLoad();
            if (loaded_clients != null) {
                clients.setClients(loaded_clients.getClients());
            }
            else {
                clients.setClients(new HashMap<>());
            }
        }
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
