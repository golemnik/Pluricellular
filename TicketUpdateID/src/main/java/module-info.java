import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.updateID.UpdateIDTCellFactory;
import com.golem.updateID.UpdateIDTCommandCell;

module Pluricellular.TicketUpdateID.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;
    requires Pluricellular.Informer.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with UpdateIDTCommandCell;
    provides AbstractTCellFactory with UpdateIDTCellFactory;
}