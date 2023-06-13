import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketInfo.InfoTCellFactory;
import com.golem.ticketInfo.InfoTCommandCell;

module Pluricellular.TicketClearCell.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with InfoTCommandCell;
    provides AbstractTCellFactory with InfoTCellFactory;
}