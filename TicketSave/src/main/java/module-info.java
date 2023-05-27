import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;
import com.golem.ticketSave.SaveCellFactory;
import com.golem.ticketSave.SaveTCommandCell;

module Pluricellular.TicketSave.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;
    requires com.google.gson;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with SaveTCommandCell;
    provides AbstractTCellFactory with SaveCellFactory;
}
