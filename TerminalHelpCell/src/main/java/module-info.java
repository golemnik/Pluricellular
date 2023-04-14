import com.golem.core.schemas.Cell;
import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.helpCommand.HelpCellFactory;
import com.golem.helpCommand.HelpCommandCell;

module Pluricellular.TerminalHelpCell.main {
    requires ColonyCore;

    uses Cell;
    uses AbstractCellFactory;

    provides Cell with HelpCommandCell;
    provides AbstractCellFactory with HelpCellFactory;
}