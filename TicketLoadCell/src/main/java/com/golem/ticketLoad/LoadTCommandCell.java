package com.golem.ticketLoad;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class LoadTCommandCell extends AbstractTicketCommand {
    private final String file = "save.json";

    public LoadTCommandCell() {
    }


    @Override
    public AbstractCommand useSignature(List<String> signature) {
        setAnswer(List.of("loaded..."));
        return this;
    }

    @Override
    public void activate() {
        JsonParser jp;
        TicketCollection t_collection;
        try {
            jp = new JsonParser(file);
            t_collection = jp.parseLoad();
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() +
                    "\nDue error to load from file, collection will be loaded from system file <<save.json>>");
            jp = new JsonParser("save.json");
            t_collection = jp.parseLoad();
        }
        manager.getTicketCollection().setCreationDate(t_collection.getCreationDate());
        manager.getTicketCollection().setCollection(t_collection.getCollection());
    }
}
