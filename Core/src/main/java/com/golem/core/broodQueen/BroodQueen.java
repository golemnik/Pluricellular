package com.golem.core.broodQueen;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;

import java.util.*;
import java.util.stream.Collectors;

public final class BroodQueen implements Cell {
    private ModuleLayer layer;

    public ModuleLayer getLayer() {
        return layer;
    }

    final private Scanner scanner = new Scanner(System.in);

    public BroodQueen () {}
    public void addLayer (ModuleLayer layer) {
        this.layer = layer;
    }

    public <T> T createBaseCell (List<T> cellList) {
        Map<String, T> choose = new HashMap<>();
        for (T cbm : cellList) {
            choose.put(cbm.getClass().getSimpleName(), cbm);
            System.out.println("- " + cbm.getClass().getSimpleName());
        }
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

    }
}
