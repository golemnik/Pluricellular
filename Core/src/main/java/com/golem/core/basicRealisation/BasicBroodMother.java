package com.golem.core.basicRealisation;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.CellFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicBroodMother implements CellBroodMother {
    private final Map<String, CellFactory> factories = new HashMap<>();

    @Override
    public List<String> getFactoryCommands() {
        return new ArrayList<>(factories.keySet());
    }

    @Override
    public void addFactory(CellFactory factory) {
        factories.put(factory.creationCommand(), factory);
    }

    @Override
    public void addFactoryList(List<CellFactory> factoryList) {
        for (CellFactory factory : factoryList) {
            addFactory(factory);
        }
    }

    @Override
    public void reloadFactoryList(List<CellFactory> factoryList) {
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
