package com.golem.core.basicRealisation;

import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.core.schemas.abstracts.AbstractInnerCellFullCore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicBroodMother extends AbstractInnerCellFullCore implements CellBroodMother {
    private final Map<String, AbstractSystemCellFactory> factories = new HashMap<>();

    @Override
    public Map<String, AbstractSystemCellFactory> getFactoryCommands() {
        return new HashMap<>(factories);
    }

    @Override
    public void addMainCellDepends(AbstractSystemCellFactory factory) {
        factory.addCore(getCoreCell());
        factory.addBroodMother(getBroodMother());
        factory.addBroodQueen(getBroodQueen());
    }

    @Override
    public void addFactory(AbstractSystemCellFactory factory) {
        addMainCellDepends(factory);
        factories.put(factory.creationCommand(), factory);
    }

    @Override
    public void addFactoryList(List<? extends AbstractSystemCellFactory> factoryList) {
        for (AbstractSystemCellFactory factory : factoryList) {
            addFactory(factory);
        }
    }

    @Override
    public void reloadFactoryList(List<? extends AbstractSystemCellFactory> factoryList) {
        factories.clear();
        addFactoryList(factoryList);
    }

    @Override
    public void clearAllFactoryList() {
        factories.clear();
    }

    @Override
    public Cell createCell(String cell) {
        try {
            return factories.get(cell).create();
        }
        catch (Exception e) {
            return new CorruptedCommandCell();
        }
    }

    @Override
    public void activate() {

    }
}
