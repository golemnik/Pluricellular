import com.golem.core.basicRealisation.*;
import com.golem.core.basicRealisation.BasicBroodMother;
import com.golem.core.basicRealisation.BasicCell;
import com.golem.core.basicRealisation.BasicTerminalCell;
import com.golem.core.basicRealisation.CorruptedCell;
import com.golem.core.basicRealisation.coreCommands.ExitCellFactory;
import com.golem.core.basicRealisation.coreCommands.ExitCommandCell;
import com.golem.core.basicRealisation.coreCommands.UpdateGenomeCellFactory;
import com.golem.core.basicRealisation.coreCommands.UpdateGenomeCommandCell;
import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.*;
import com.golem.core.schemas.abstracts.AbstractCellFactory;

module ColonyCore {
    exports com.golem.core.schemas;
    exports com.golem.core.coreCell;
    exports com.golem.core.broodQueen;
    exports com.golem.core.schemas.connections;
    exports com.golem.core.schemas.abstracts;

    uses Cell;
    uses CellFactory;
    uses CellBroodMother;
    uses TerminalCell;
    uses AbstractCellFactory;

    provides Cell with BasicCell, CorruptedCell, BroodQueen, CoreCell, ExitCommandCell, UpdateGenomeCommandCell;
    provides AbstractCellFactory with BasicCellFactory, ExitCellFactory, UpdateGenomeCellFactory;
    provides CellBroodMother with BasicBroodMother;
    provides TerminalCell with BasicTerminalCell;
}

// java --module-path .\build\libs\Pluricellular-1.0.jar -m core/com.golem.core.Main
// use it to build simple core and full path to main class.