import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.netCell.innerMechanics.NetConnection;
import com.golem.serverCell.clients.infoCom.InfoCCellFactory;
import com.golem.serverCell.clients.infoCom.InfoCCommandCell;
import com.golem.serverCell.serverCommands.AbstractSCellFactory;
import com.golem.serverCell.serverCommands.ServerQueen;
import com.golem.serverCell.transmitter.Transmitter;

module Pluricellular.ServerCell.main {
    requires ColonyCore;
    requires Pluricellular.NetCell.main;
    requires org.apache.logging.log4j;

    exports com.golem.serverCell.serverCommands;
    exports com.golem.serverCell.clients;

    opens com.golem.serverCell.clients to com.google.gson;

    uses AbstractQueenCell;
    uses AbstractSCellFactory;
    uses Cell;
    uses AbstractCellFactory;

    provides NetConnection with Transmitter;
    provides AbstractQueenCell with ServerQueen;

    provides Cell with InfoCCommandCell;
    provides AbstractCellFactory with InfoCCellFactory;
}