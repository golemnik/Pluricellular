import com.golem.core.basicRealisation.*;
import com.golem.core.basicRealisation.BasicBroodMother;
import com.golem.core.basicRealisation.BasicCell;
import com.golem.core.basicRealisation.BasicTerminalCell;
import com.golem.core.basicRealisation.coreCommands.CorruptedCommandCell;
import com.golem.core.basicRealisation.coreCommands.*;
import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.*;
import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.schemas.abstracts.AbstractSystemCellFactory;
import com.golem.core.schemas.deepSchemas.SystemCommand;

module ColonyCore {
    exports com.golem.core.schemas;
    exports com.golem.core.coreCell;
    exports com.golem.core.broodQueen;
    exports com.golem.core.schemas.connections;
    exports com.golem.core.schemas.abstracts;
    exports com.golem.core.schemas.deepSchemas;

    uses Cell;
    uses CellBroodMother;
    uses TerminalCell;
    uses AbstractCellFactory;
    uses AbstractSystemCellFactory;

    provides Cell with BasicCell, CorruptedCommandCell, BroodQueen,
            CoreCell, ExitCommandCell, ReloadGenomeCommandCell, UnloadGenomeCellCommand;
    provides SystemCommand with ExitCommandCell, ReloadGenomeCommandCell, UnloadGenomeCellCommand;
    provides AbstractCellFactory with BasicCellFactory;
    provides AbstractSystemCellFactory with ExitCellFactory, ReloadGenomeCellFactory, UnloadGenomeCellFactory;

    provides CellBroodMother with BasicBroodMother;
    provides TerminalCell with BasicTerminalCell;
}

// java --module-path .\build\libs\Pluricellular-1.0.jar -m core/com.golem.core.Main
// use it to build simple core and full path to main class.