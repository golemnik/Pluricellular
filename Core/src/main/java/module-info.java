import com.golem.core.basicRealisation.*;
import com.golem.core.basicRealisation.BasicBroodMotherCell;
import com.golem.core.basicRealisation.BasicCell;
import com.golem.core.basicRealisation.BasicTerminalCell;
import com.golem.core.queens.SystemQueen;
import com.golem.core.schemas.abstracts.AbstractQueenCell;
import com.golem.core.schemas.abstracts.AbstractTerminal;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;
import com.golem.core.basicRealisation.coreCommands.*;
import com.golem.core.queens.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.*;
import com.golem.core.schemas.abstracts.AbstractCellFactory;
import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.core.schemas.deepSchemas.SystemCommand;

module ColonyCore {
    exports com.golem.core.schemas;
    exports com.golem.core.coreCell;
    exports com.golem.core.queens;
    exports com.golem.core.schemas.connections;
    exports com.golem.core.schemas.abstracts;
    exports com.golem.core.schemas.deepSchemas;
    exports com.golem.core.schemas.providedRealisations;
    exports com.golem.core.innerMechanisms;

    uses Cell;
    uses BroodMotherCell;
    uses TerminalCell;
    uses AbstractCellFactory;
    uses AbstractSystemCellFactory;
    uses AbstractQueenCell;
    uses AbstractTerminal;

    provides Cell with BasicCell, CorruptedCommandCell,
            CoreCell, ExitCommandCell, ReloadGenomeCommandCell, UnloadGenomeCellCommand;
    provides AbstractQueenCell with BroodQueen, SystemQueen;
    provides SystemCommand with ExitCommandCell, ReloadGenomeCommandCell, UnloadGenomeCellCommand;
    provides AbstractCellFactory with BasicCellFactory;
    provides AbstractSystemCellFactory with ExitCellFactory, ReloadGenomeCellFactory, UnloadGenomeCellFactory;

    provides BroodMotherCell with BasicBroodMotherCell;
    provides AbstractTerminal with BasicTerminalCell;
}

// java --module-path .\build\libs\Pluricellular-1.0.jar -m core/com.golem.core.Main
// use it to build simple core and full path to main class.