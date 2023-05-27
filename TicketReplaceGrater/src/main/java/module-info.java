import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.replaceGreater.ReplaceGreaterTCellFactory;
import com.golem.replaceGreater.ReplaceGreaterTCommandCell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketReplaceGrater.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with ReplaceGreaterTCommandCell;
    provides AbstractTCellFactory with ReplaceGreaterTCellFactory;
}