import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.printFieldDescending.DescendPrintTCellFactory;
import com.golem.printFieldDescending.DescendPrintTCommandCell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketPrintFieldDescending.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with DescendPrintTCommandCell;
    provides AbstractTCellFactory with DescendPrintTCellFactory;
}