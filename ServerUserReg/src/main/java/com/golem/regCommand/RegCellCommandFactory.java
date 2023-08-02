package com.golem.regCommand;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;
import com.golem.serverCell.serverCommands.AbstractSCellFactory;

import java.util.ArrayList;
import java.util.List;

public class RegCellCommandFactory extends AbstractSCellFactory {
    public RegCellCommandFactory() {
        super(new Signature(
                "register",
                "register new client to the system.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("^" + "register \\w{3,26} \\w{3,26}" + "$")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }
    @Override
    public AbstractCommand create(List<String> signature) {
        RegCommandCell cell = new RegCommandCell();
        cell.setClients(getClients());
        return cell.useSignature(signature);
    }
}


