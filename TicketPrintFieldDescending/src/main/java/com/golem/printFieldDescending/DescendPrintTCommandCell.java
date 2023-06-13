package com.golem.printFieldDescending;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.ticketCell.collection.TicketCollection;
import com.golem.ticketCell.collection.ticket.InversedTicketTypeComparator;
import com.golem.ticketCell.collection.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

public class DescendPrintTCommandCell extends AbstractCommand {
    private TicketCollection collection;
    public DescendPrintTCommandCell() {
    }

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void activate() {
        List<String> answer = new ArrayList<>();
        answer.add("Used types:");
        if (collection != null) {
            List<Ticket> list = new ArrayList<>(collection.getCollection().values());
            list.sort(new InversedTicketTypeComparator());
            for (Ticket t : list) {
                answer.add(t.getType().toString());
            }
            setAnswer(answer);
            return;
        }
        setAnswer(List.of("Collection is not exist."));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
