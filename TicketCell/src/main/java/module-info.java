import com.golem.core.schemas.Cell;
import com.golem.core.innerMechanisms.AbstractSystemCellFactory;
import com.golem.core.schemas.abstracts.AbstractQueenCell;
import com.golem.ticketCell.TicketQueen;

module Pluricellular.TicketCell.main {
    requires ColonyCore;

    exports com.golem.ticketCell.schemas;
    exports com.golem.ticketCell.collection;
    exports com.golem.ticketCell.collection.ticket;

    uses Cell;
    uses AbstractSystemCellFactory;
    uses com.golem.ticketCell.schemas.AbstractTCellFactory;
    uses AbstractQueenCell;

    provides AbstractQueenCell with TicketQueen;
}