import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.filterGreaterThenVenue.FilterGreaterVenueTCellFactory;
import com.golem.filterGreaterThenVenue.FilterGreaterVenueTCommandCell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketFilterGreaterThenVenue.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with FilterGreaterVenueTCommandCell;
    provides AbstractTCellFactory with FilterGreaterVenueTCellFactory;

}