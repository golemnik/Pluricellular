package com.golem.ticketCountLessPrice;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.access.AbstractTicketCommand;

import java.util.List;

public class CountLessPriceTCell extends AbstractTicketCommand implements Cell {
    private Double ticketPrice;

    @Override
    public void activate() {
        Long counter = manager.getTicketCollection().getCollection().values()
                .stream()
                .filter(x -> x.getPrice() < ticketPrice)
                .count();
        setAnswer(List.of("Amount elements which price less than <<" +
                CellPrinter.Colorist.PURPLE(ticketPrice.toString()) + ">>: ",
                String.valueOf(counter)));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        ticketPrice = Double.parseDouble(signature.get(0).split(" ")[1]);
        return this;
    }
}
