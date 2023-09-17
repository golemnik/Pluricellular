package com.golem;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.serverCell.serverCommands.AbstractSCellFactory;

import java.util.ArrayList;
import java.util.List;

public class ResetCellFactory extends AbstractSCellFactory {
    public ResetCellFactory() {
            super(new Signature(
                    "reset",
                    "reset current password in the system.",
                    SignatureStatus.CONNECTED,
                    new ArrayList<>(List.of("^" + "reset \\w{3,26} \\w{3,26} \\w{3,26}" + "$")),
                    new ArrayList<>(List.of("")),
                    new ArrayList<>(List.of("unsupported arguments"))));
        }
        @Override
        public AbstractCommand create(List<String> signature) {
            ResetCommandCell cell = new ResetCommandCell();
            cell.setClients(getClients());
            return cell.useSignature(signature);
        }
}
