package com.golem.core.schemas.providedRealisations;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;

import java.util.List;

public class CorruptedCommandCell extends AbstractCommand implements SystemCommand {
    private final String corruptionReason;

    public String getCorruptionReason() {
        return corruptionReason;
    }

    public CorruptedCommandCell () {
        this.corruptionReason = "signature was corrupted";
    }
    public CorruptedCommandCell (String corruptionReason) {
        this.corruptionReason = corruptionReason;
    }
    @Override
    public void activate() {
        if (corruptionReason.equals("")) {
            setAnswer("Smthing went wrong... this cell is corrupted one.");
            return;
        }
        setAnswer(corruptionReason);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
