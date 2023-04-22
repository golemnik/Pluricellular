package com.golem.core.broodQueen;

import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.TerminalCell;
import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.schemas.abstracts.AbstractSystemCellFactory;

import java.util.*;

public final class BroodQueen implements Cell {
    private ModuleLayer layer;

    public ModuleLayer getLayer() {
        return layer;
    }
    final private Scanner scanner = new Scanner(System.in);

    public BroodQueen () {}
    public void addCoreLayer(ModuleLayer layer) {
        this.layer = layer;
    }
    public void updateLayer() {
        CellLayer.reloadLayer();
        updateQueenLayer();
    }
    public void updateQueenLayer() {
        this.layer = CellLayer.getLayer();
    }
    public <T> T createBaseCell (List<T> cellList) {
        Map<String, T> choose = new HashMap<>();
        if (cellList.size() == 1) {
            return cellList.get(0);
        }
        String print = "";
        for (T cbm : cellList) {
            choose.put(cbm.getClass().getSimpleName(), cbm);
            print += "- " + cbm.getClass().getSimpleName() + "\n";
        }
        System.out.println(print);
        T result;
        do {
            result = choose.get(scanner.nextLine());
            if (result == null) {
                System.out.println("incorrect choose...");
            }
        } while (result == null);
        return result;
    }

    @Override
    public void activate() {
        updateLayer();
    }

    public static List<AbstractCellFactory> loadOuterFactories(ModuleLayer layer) {
        return AbstractCellFactory.getCellFactories(layer);
    }
    public static List<AbstractSystemCellFactory> loadSystemFactories(ModuleLayer layer) {
        return AbstractSystemCellFactory.getSystemCellFactories(layer);
    }
    public static List<CellBroodMother> loadBroodMothers (ModuleLayer layer) {
        return CellBroodMother.getCellBroodMothers(layer);
    }
    public static List<TerminalCell> loadTerminals (ModuleLayer layer) {
        return TerminalCell.getCellFactories(layer);
    }
}
