import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellFactory;
import com.golem.shoutingTwice.ShoutingTwiceCell;
import com.golem.shoutingTwice.ShoutingTwiceCellFactory;

module Pluricellular.ShoutingTwice {
    requires ColonyCore;
    requires Pluricellular.ShoutingCell;

    provides Cell with ShoutingTwiceCell;
    provides CellFactory with ShoutingTwiceCellFactory;
}