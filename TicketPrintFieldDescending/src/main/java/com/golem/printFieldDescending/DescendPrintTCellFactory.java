package com.golem.printFieldDescending;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class DescendPrintTCellFactory extends AbstractTCellFactory {
    public DescendPrintTCellFactory() {
        super (new Signature(
                "print_field_type",
                "display the value of the type field of all elements in descending order.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of("print_field_type")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        DescendPrintTCommandCell cell = new DescendPrintTCommandCell();
        cell.setManager(getManager());
        return cell;
    }
}
