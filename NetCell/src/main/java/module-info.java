import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.netCell.NetTerminal;

module Pluricellular.NetCell.main {
    requires ColonyCore;

    exports com.golem.netCell.containers;
    exports com.golem.netCell.innerMechanics;

    uses AbstractTerminal;
    uses com.golem.netCell.innerMechanics.NetConnection;

    provides AbstractTerminal with NetTerminal;

}