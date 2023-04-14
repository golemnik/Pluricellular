package com.golem.core.basicRealisation.coreCommands;

import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.Cell;

public class UnloadGenomeCellCommand implements Cell {
    @Override
    public void activate() {
//        CellLayer.UnloadLayer();
        System.out.println("it is not working yet...");
    }
}

// добавить brood и queen, обновить им состояние слоя (выгружен) при вызове команды.
// сделать команду загрузки слоя (проверить на это reloadGenomeCommand)
//
