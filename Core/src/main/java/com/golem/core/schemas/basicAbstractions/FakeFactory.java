package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class FakeFactory extends AbstractCellFactory {
    public FakeFactory () {
        super(new Signature("",
                "",
                SignatureStatus.FAKE,
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of(""))));
    }
    public FakeFactory (String command, String description, SignatureStatus status) {
        super(new Signature(
                command,
                description,
                status,
                new ArrayList<>(List.of(command)),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        return new CorruptedCommandCell("Was used fake factory " +
                "and command doesn't exist near this core and was provided.");
    }
}
