package com.golem.core.schemas.basicRealisation;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;

import java.util.List;

public class BasicCell extends AbstractCommand {
    @Override
    public void activate() {
        setAnswer("This is Basic Cell activation.");
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
