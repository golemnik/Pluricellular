import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.netCell.NetTerminal;

module Pluricellular.NetCell.main {
    requires ColonyCore;

    uses AbstractTerminal;

    provides AbstractTerminal with NetTerminal;

}