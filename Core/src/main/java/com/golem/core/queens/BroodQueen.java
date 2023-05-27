package com.golem.core.queens;

import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;

import java.util.*;

public final class BroodQueen extends AbstractQueenCell {

    @Override
    public void activate() {
    }
    @Override
    public List<AbstractCellFactory> extractFactories(ModuleLayer layer) {
        return AbstractCellFactory.getCellFactories(layer);
    }
}
