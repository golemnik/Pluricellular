import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.userSave.SaveCCellFactory;
import com.golem.userSave.SaveCCommandCell;

module Pluricellular.UserSave.main {
    requires ColonyCore;
    requires com.google.gson;
    requires Pluricellular.ServerCell.main;

    uses Cell;
    uses AbstractCellFactory;

    provides Cell with SaveCCommandCell;
    provides AbstractCellFactory with SaveCCellFactory;
}