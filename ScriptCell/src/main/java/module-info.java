import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.fileCell.fileReader.SimpleReader;
import com.golem.scriptCell.ExecuteScriptCellFactory;
import com.golem.scriptCell.ExecuteScriptCommandCell;

module Pluricellular.ScriptCell.main {
    requires ColonyCore;
    requires Pluricellular.FileSystemCell.main;

    uses SimpleReader;

    provides AbstractCellFactory with ExecuteScriptCellFactory;
    provides Cell with ExecuteScriptCommandCell;
}