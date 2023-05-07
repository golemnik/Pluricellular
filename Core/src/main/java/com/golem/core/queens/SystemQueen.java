package com.golem.core.queens;

import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.core.schemas.abstracts.AbstractQueenCell;

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
