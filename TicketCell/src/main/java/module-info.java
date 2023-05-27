import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.ticketCell.TicketQueen;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketCell.schemas.TicketCellCommand;

open module Pluricellular.TicketCell.main {
    requires ColonyCore;

    exports com.golem.ticketCell.schemas;
    exports com.golem.ticketCell.collection;
    exports com.golem.ticketCell.collection.ticket;

    uses AbstractQueenCell;
    uses AbstractTCellFactory;
    uses TicketCellCommand;

    provides AbstractQueenCell with TicketQueen;
}