package com.golem.core.schemas.basicRealisation;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;

import java.util.ArrayList;
import java.util.List;

public class BasicCellFactory extends AbstractCellFactory {
    public BasicCellFactory () {
        super(new Signature(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
    public String creationCommand() {
        return "basic";
    }

    @Override
    public String commandDescription() {
        return "do nothing. Simple testing cell, basic realisation.";
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        if (signatureCompare(signature)) return new BasicCell().useSignature(signature);
        return new CorruptedCommandCell();
    }
}
