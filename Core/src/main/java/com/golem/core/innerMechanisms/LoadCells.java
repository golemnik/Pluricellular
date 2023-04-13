package com.golem.core.innerMechanisms;

import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.CellFactory;
import com.golem.core.schemas.TerminalCell;

import java.util.List;

public class LoadCells {
    public static List<CellFactory> loadFactories(ModuleLayer layer) {
        return CellFactory.getCellFactories(layer);
    }
    public static List<CellBroodMother> loadBroodMothers (ModuleLayer layer) {
        return CellBroodMother.getCellBroodMothers(layer);
    }
    public static List<TerminalCell> loadTerminals (ModuleLayer layer) {
        return TerminalCell.getCellFactories(layer);
    }
}
