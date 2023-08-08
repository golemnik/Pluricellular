package com.golem.ticketCountLessPrice;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.access.AbstractTicketCommand;

import java.util.List;

public class CountLessPriceTCell extends AbstractTicketCommand implements Cell {
    private Double ticketPrice;
    public CountLessPriceTCell(){}

    @Override
    public void activate() {
        int counter = 0;
        for (String s : manager.getTicketCollection().getCollection().keySet()) {
            counter += manager.getTicketCollection().getCollection().get(s).getPrice() < ticketPrice ? 1 : 0;
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
