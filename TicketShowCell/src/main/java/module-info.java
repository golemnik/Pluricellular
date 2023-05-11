import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketShowCell.ShowTCellFactory;
import com.golem.ticketShowCell.ShowTCommandCell;

module Pluricellular.TicketShowCell.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with ShowTCommandCell;
    provides AbstractTCellFactory with ShowTCellFactory;
}