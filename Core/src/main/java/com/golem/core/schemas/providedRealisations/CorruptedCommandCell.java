package com.golem.core.schemas.providedRealisations;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.deepSchemas.SystemCommand;

public class CorruptedCommandCell implements Cell, SystemCommand {
    @Override
    public void activate() {
        System.out.println("Smthing went wrong... this cell is corrupted one.");
    }
}
