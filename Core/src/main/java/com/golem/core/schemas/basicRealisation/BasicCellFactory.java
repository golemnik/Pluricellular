package com.golem.core.schemas.basicRealisation;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class BasicCellFactory extends AbstractCellFactory {
    public BasicCellFactory () {
        super(new Signature(
                "basic",
                "do nothing. Simple testing cell, basic realisation.",
                SignatureStatus.CONNECTED,
                new ArrayList<>(List.of("basic")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        if (signatureCompare(signature)) return new BasicCell().useSignature(signature);
        return new CorruptedCommandCell();
    }
}
