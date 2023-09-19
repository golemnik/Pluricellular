import com.golem.core.queens.*;
import com.golem.core.schemas.basicAbstractions.*;
import com.golem.core.schemas.basicRealisation.BasicBroodMotherCell;
import com.golem.core.schemas.basicRealisation.BasicCell;
import com.golem.core.schemas.basicRealisation.BasicCellFactory;
import com.golem.core.schemas.basicRealisation.BasicTerminalCell;
import com.golem.core.schemas.basicInterfaces.*;
import com.golem.core.schemas.basicInterfaces.deepSchemas.*;
import com.golem.core.schemas.basicRealisation.coreCommands.*;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;
import com.golem.core.queens.BroodQueen;
import com.golem.core.coreCell.CoreCell;

module ColonyCore {
    exports com.golem.core.innerMechanisms;
    exports com.golem.core.schemas.providedRealisations;

    exports com.golem.core.schemas.basicAbstractions;

    exports com.golem.core.schemas.basicInterfaces;
    exports com.golem.core.schemas.basicInterfaces.deepSchemas;
    exports com.golem.core.schemas.basicInterfaces.connections;
    exports com.golem.core.schemas.signature;

    uses Cell;
    uses AbstractBroodMother;
    uses AbstractCellFactory;
    uses AbstractSystemCellFactory;
    uses AbstractQueenCell;
    uses AbstractTerminal;

    provides Cell with BasicCell, CorruptedCommandCell,
            CoreCell, ExitCommandCell;
    provides AbstractQueenCell with BroodQueen, SystemQueen;
    provides SystemCommand with ExitCommandCell;
    provides AbstractCellFactory with BasicCellFactory, FakeFactory;
    provides AbstractSystemCellFactory with ExitCellFactory;

    provides AbstractBroodMother with BasicBroodMotherCell;
    provides AbstractTerminal with BasicTerminalCell;
}
