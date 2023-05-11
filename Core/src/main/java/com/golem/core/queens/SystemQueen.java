package com.golem.core.queens;

import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;

import java.util.List;

public class SystemQueen extends AbstractQueenCell {
    @Override
    public void activate() {

    }
    @Override
    public List<AbstractSystemCellFactory> extractFactories(ModuleLayer layer) {
        return AbstractSystemCellFactory.getSystemCellFactories(layer);
    }
}
