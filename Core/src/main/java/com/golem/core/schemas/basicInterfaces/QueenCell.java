package com.golem.core.schemas.basicInterfaces;

import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;

import java.util.List;

public interface QueenCell extends Cell {
    ModuleLayer getLayer();
    void addLayer(ModuleLayer layer);
    void updateLayer();
    void updateQueenLayer();
    <T extends AbstractSystemCellFactory> List<T> extractFactories(ModuleLayer layer);
    <T extends AbstractSystemCellFactory> void fillFactories(List <T> factories);
    <T> T createBaseCell (List<T> cellList);
}
