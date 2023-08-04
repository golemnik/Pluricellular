package com.golem.ticketSave;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class SaveTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    private final String file = "save.json";

    public SaveTCommandCell() {
    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        setAnswer(List.of("saving ticket collection.."));
        return this;
    }

    @Override
    public void activate() {
        JsonParser jp;
        try {
            jp = new JsonParser(file);
            jp.parseSave(collection);
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() +
                    "\nDue error to save in file, collection will be saved in system file <<save.json>>");
            jp = new JsonParser("save.json");
            jp.parseSave(collection);
        }
    }
}
