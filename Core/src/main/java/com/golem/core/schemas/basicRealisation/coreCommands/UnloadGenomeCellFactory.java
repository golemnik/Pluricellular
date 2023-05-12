//package com.golem.core.schemas.basicRealisation.coreCommands;
//
//import com.golem.core.schemas.basicInterfaces.Cell;
//import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
//
//public class UnloadGenomeCellFactory extends AbstractSystemCellFactory {
//    @Override
//    public Cell create() {
//        UnloadGenomeCellCommand cell = new UnloadGenomeCellCommand();
//        cell.setBroodMother(getBroodMother());
//        return cell;
//    }
//
//    @Override
//    public String creationCommand() {
//        return "unload_genome";
//    }
//
//    @Override
//    public String commandDescription() {
//        return "clears all modules from the current layer.";
//    }
//}
