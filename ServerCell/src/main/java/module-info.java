import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.netCell.innerMechanics.NetConnection;
import com.golem.serverCell.serverCommands.AbstractSCellFactory;
import com.golem.serverCell.serverCommands.ServerQueen;
import com.golem.serverCell.transmitter.Transmitter;

module Pluricellular.ServerCell.main {
    requires ColonyCore;
    requires Pluricellular.NetCell.main;
    requires org.apache.logging.log4j;

    exports com.golem.serverCell.serverCommands;
    exports com.golem.serverCell.clients;

    uses AbstractQueenCell;
    uses AbstractSCellFactory;

    provides NetConnection with Transmitter;
    provides AbstractQueenCell with ServerQueen;
}