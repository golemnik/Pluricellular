import com.golem.core.basicRealisation.*;
import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.CellFactory;
import com.golem.core.schemas.TerminalCell;

module ColonyCore {
    exports com.golem.core.schemas;
    exports com.golem.core.coreCell;
    exports com.golem.core.broodQueen;

    uses Cell;
    uses CellFactory;
    uses CellBroodMother;
    uses TerminalCell;

    provides Cell with BasicCell, CorruptedCell, BroodQueen, CoreCell;
    provides CellFactory with BasicCellFactory;
    provides CellBroodMother with BasicBroodMother;
    provides TerminalCell with BasicTerminalCell;
}

// java --module-path .\build\libs\Pluricellular-1.0.jar -m core/com.golem.core.Main
// use it to build simple core and full path to main class.