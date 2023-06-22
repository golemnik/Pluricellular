package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBroodMother extends AbstractExtendedICellCore implements BroodMotherCell {
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
    public AbstractCommand createCell(String cell, List<String> signature) {
        AbstractCommand n_cell = factories.get(cell).create(signature);
        if (n_cell == null) return new CorruptedCommandCell().useSignature(signature);
        return n_cell;
    }

    @Override
    public void activate() {

    }
}
