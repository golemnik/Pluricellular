import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketLoad.LoadCellFactory;
import com.golem.ticketLoad.LoadTCommandCell;

module Pluricellular.TicketLoadCell.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;
    requires com.google.gson;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with LoadTCommandCell;
    provides AbstractTCellFactory with LoadCellFactory;
}