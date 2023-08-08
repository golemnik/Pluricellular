package com.golem.ticketCountLessPrice;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketCell.schemas.SignatureRegex;

import java.util.ArrayList;
import java.util.List;

public class CountLessPriceTCellFactory extends AbstractTCellFactory {
    public CountLessPriceTCellFactory () {
        super(new Signature(
                "count_less_price",
                "count collection elements less.",
                SignatureStatus.PROVIDED,
                new ArrayList<>(List.of("count_less_price " + SignatureRegex.ID)),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }
    @Override
    public AbstractCommand create(List<String> signature) {
        CountLessPriceTCell cell = new CountLessPriceTCell();
        cell.setManager(getManager());
        return cell.useSignature(signature);
    }
}
