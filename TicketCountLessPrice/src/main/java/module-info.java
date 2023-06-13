import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketCountLessPrice.CountLessPriceTCell;
import com.golem.ticketCountLessPrice.CountLessPriceTCellFactory;

module Pluricellular.TicketCountLessPrice.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with CountLessPriceTCell;
    provides AbstractTCellFactory with CountLessPriceTCellFactory;
}