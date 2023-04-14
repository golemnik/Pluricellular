package com.golem.core.basicRealisation;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.schemas.abstracts.AbstractInnerCellFullCore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicBroodMother extends AbstractInnerCellFullCore implements CellBroodMother {
    private final Map<String, AbstractCellFactory> factories = new HashMap<>();

    @Override
    public Map<String, AbstractCellFactory> getFactoryCommands() {
        return new HashMap<>(factories);
    }

    @Override
    public void addMainCellDepends(AbstractCellFactory factory) {
        factory.addCore(getCoreCell());
        factory.addBroodMother(getBroodMother());
        factory.addBroodQueen(getBroodQueen());
    }

    @Override
    public void addFactory(AbstractCellFactory factory) {
        addMainCellDepends(factory);
        factories.put(factory.creationCommand(), factory);
    }

    @Override
    public void addFactoryList(List<AbstractCellFactory> factoryList) {
        for (AbstractCellFactory factory : factoryList) {
            addMainCellDepends(factory);
            addFactory(factory);
        }
    }

    @Override
    public void reloadFactoryList(List<AbstractCellFactory> factoryList) {
        factories.clear();
        addFactoryList(factoryList);
    }

    @Override
    public Cell createCell(String cell) {
        try {
            return factories.get(cell).create();
        }
        catch (Exception e) {
            return new CorruptedCell();
        }
    }

    @Override
    public void activate() {

    }
}
