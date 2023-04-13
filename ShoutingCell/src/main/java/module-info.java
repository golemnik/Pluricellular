import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellFactory;
import com.golem.shoutingCell.ShoutingCell;
import com.golem.shoutingCell.ShoutingCellFactory;

module Pluricellular.ShoutingCell.main {
    requires ColonyCore;

    provides Cell with ShoutingCell;
    provides CellFactory with ShoutingCellFactory;
}