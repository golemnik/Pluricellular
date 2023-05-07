package com.golem.core.schemas.abstracts;

import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.core.schemas.BroodMotherCell;
import com.golem.core.schemas.Cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractBroodMother extends AbstractExtendedICellCore implements BroodMotherCell {
    private final Map<String, AbstractSystemCellFactory> factories = new HashMap<>();
    @Override
    public Map<String, AbstractSystemCellFactory> getFactoryCommands() {
        return new HashMap<>(factories);
    }

    @Override
    public <T extends AbstractSystemCellFactory> void addMainCellDepends(T factory, AbstractQueenCell queen) {
        factory.addCore(getCoreCell());
        factory.addBroodMother(getBroodMother());
        factory.addQueenConnection(queen);
    }

    @Override
    public <T extends AbstractSystemCellFactory> void addFactory(T factory, AbstractQueenCell queen) {
        addMainCellDepends(factory, queen);
        factories.put(factory.creationCommand(), factory);
    }

    @Override
    public <T extends AbstractSystemCellFactory> void addFactoryList(List<T> factoryList, AbstractQueenCell queen) {
        for (AbstractSystemCellFactory factory : factoryList) {
            addFactory(factory, queen);
        }
    }

    @Override
    public <T extends AbstractSystemCellFactory> void reloadFactoryList(List<T> factoryList, List<AbstractQueenCell> queens) {
        factories.clear();
        queens.forEach(queen -> addFactoryList(factoryList, queen));
    }

    @Override
    public void clearAllFactoryList() {
        factories.clear();
    }

    @Override
    public Cell createCell(String cell) {
        return factories.get(cell).create();
    }

    @Override
    public void activate() {

    }
}
