package com.golem.ticketSave;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractTicketCommand;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class SaveTCommandCell extends AbstractTicketCommand {
    private final String file = "save.json";

    public SaveTCommandCell() {
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
            jp.parseSave(manager.getTicketCollection());
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() +
                    "\nDue error to save in file, collection will be saved in system file <<save.json>>");
            jp = new JsonParser("save.json");
            jp.parseSave(manager.getTicketCollection());
        }
    }
}
