import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.ticketCell.TicketQueen;
import com.golem.ticketCell.access.AbstractAccess;
import com.golem.ticketCell.access.AccessManager;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

open module Pluricellular.TicketCell.main {
    requires ColonyCore;

    exports com.golem.ticketCell.schemas;
    exports com.golem.ticketCell.collection;
    exports com.golem.ticketCell.access;
    exports com.golem.ticketCell.collection.ticket;
    exports com.golem.ticketCell.exception;

    uses AbstractQueenCell;
    uses AbstractTCellFactory;
    uses AbstractAccess;

    provides AbstractAccess with AccessManager;
    provides AbstractQueenCell with TicketQueen;
}