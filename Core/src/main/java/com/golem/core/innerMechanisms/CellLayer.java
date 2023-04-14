package com.golem.core.innerMechanisms;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CellLayer {
    private static ModuleLayer layer;
    private static String path;
    public static void loadLayer(String genomePath) {
        Path cellDir = Paths.get(genomePath); // cells storage place

        ModuleFinder cellFinder = ModuleFinder.of(cellDir); // cell finder to storage

        List<String> cells = cellFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());
        // return all cells from the genome (folder with modules)

        Configuration cellsConfig = ModuleLayer
                .boot()
                .configuration()
                .resolve(cellFinder, ModuleFinder.of(), cells);
        // config check dependency for all modules in the list as a dependency graph

        layer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(cellsConfig, ClassLoader.getSystemClassLoader());
        // create layer for cells
    }
    public static ModuleLayer getLayer () {
        return layer;
    }
    public static void UnloadLayer () {
        layer = ModuleLayer.empty();
    }
    public static void reloadLayer () {
        loadLayer(path);
    }
    public static void setPath (String path) {
        CellLayer.path = path;
    }
}
