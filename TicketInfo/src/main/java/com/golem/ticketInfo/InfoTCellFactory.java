package com.golem.ticketInfo;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

import java.util.ArrayList;
import java.util.List;

public class InfoTCellFactory extends AbstractTCellFactory {
    public InfoTCellFactory() {
        super (new Signature(
                "info",
                "show the general information about the collection.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of("info")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        InfoTCommandCell cell = new InfoTCommandCell();
        cell.setManager(getManager());
        return cell;
    }
}
