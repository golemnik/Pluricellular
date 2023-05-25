package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommandCellFactory;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class ExitCellFactory extends AbstractSystemCellFactory implements SystemCommandCellFactory {
    public ExitCellFactory() {
        super(new Signature(
                "exit",
                "shut the core cell down.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("exit")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        return new ExitCommandCell().useSignature(signature);
    }
}
