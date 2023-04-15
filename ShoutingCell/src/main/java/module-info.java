import com.golem.core.schemas.Cell;
import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.shoutingCell.ShoutingCell;
import com.golem.shoutingCell.ShoutingCellFactory;

module Pluricellular.ShoutingCell {
    requires ColonyCore;

    exports com.golem.shoutingCell;

    provides Cell with ShoutingCell;
    provides AbstractCellFactory with ShoutingCellFactory;
}