package com.golem.core.schemas.abstracts;

import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.QueenCell;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractQueenCell extends AbstractICellCore implements QueenCell {
    private ModuleLayer layer;

    public ModuleLayer getLayer() {
        return layer;
    }
    public void addLayer(ModuleLayer layer) {
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
        Scanner scanner = new Scanner(System.in);
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

    public static List<AbstractQueenCell> getQueens(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractQueenCell.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
