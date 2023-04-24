import com.golem.core.schemas.Cell;
import com.golem.core.schemas.ConnectedCell;
import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.ticketCell.TicketDataConnectorCell;

module Pluricellular.TicketCell.main {
    requires ColonyCore;

    exports com.golem.ticketCell.schemas;
    exports com.golem.ticketCell.collection;
    exports com.golem.ticketCell.collection.ticket;

    uses Cell;
    uses AbstractSystemCellFactory;
    uses com.golem.ticketCell.schemas.AbstractTCellFactory;

    provides ConnectedCell with TicketDataConnectorCell;
}