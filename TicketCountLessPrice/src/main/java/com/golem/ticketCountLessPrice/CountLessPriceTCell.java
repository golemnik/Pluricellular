package com.golem.ticketCountLessPrice;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.collection.TicketCollection;

import java.util.ArrayList;
import java.util.List;

public class CountLessPriceTCell extends AbstractCommand implements Cell {
    private TicketCollection collection;
    private Double ticketPrice;
    public CountLessPriceTCell(){}

    public void setCollection(TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void activate() {
        int counter = 0;
        for (String s : collection.getCollection().keySet()) {
            counter += collection.getCollection().get(s).getPrice() < ticketPrice ? 1 : 0;
        }
        setAnswer(List.of("Amount elements which price less than <<" +
                CellPrinter.Colorist.PURPLE(ticketPrice.toString()) + ">>:\n"+
                CellPrinter.Colorist.PURPLE(String.valueOf(counter))));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        ticketPrice = Double.parseDouble(signature.get(0).split(" ")[1]);
        return this;
    }
}
