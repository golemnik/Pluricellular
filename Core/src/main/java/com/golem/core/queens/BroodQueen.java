package com.golem.core.queens;

import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.schemas.abstracts.AbstractQueenCell;

import java.util.*;

public final class BroodQueen extends AbstractQueenCell {

    @Override
    public void activate() {
        updateLayer();
    }
    @Override
    public List<AbstractCellFactory> extractFactories(ModuleLayer layer) {
        return AbstractCellFactory.getCellFactories(layer);
    }
}
