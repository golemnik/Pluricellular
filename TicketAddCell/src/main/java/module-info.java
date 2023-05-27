import com.golem.addCell.InsertTCellFactory;
import com.golem.addCell.InsertTCommandCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketAddCell.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with InsertTCommandCell;
    provides AbstractTCellFactory with InsertTCellFactory;
}