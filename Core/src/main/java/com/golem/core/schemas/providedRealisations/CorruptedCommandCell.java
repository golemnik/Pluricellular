package com.golem.core.schemas.providedRealisations;

import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicInterfaces.deepSchemas.SystemCommand;

public class CorruptedCommandCell implements Cell, SystemCommand {
    @Override
    public void activate() {
        System.out.println("Smthing went wrong... this cell is corrupted one.");
    }
}
