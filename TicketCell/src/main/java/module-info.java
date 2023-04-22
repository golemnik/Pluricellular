import com.golem.core.schemas.Cell;
import com.golem.core.schemas.abstracts.AbstractSystemCellFactory;
import com.golem.ticketCell.TicketCollectionCell;
import com.golem.ticketCell.TicketCollectionCellFactory;

module Pluricellular.TicketCell.main {
    requires ColonyCore;

    uses Cell;
    uses AbstractSystemCellFactory;

    provides Cell with TicketCollectionCell;
    provides AbstractSystemCellFactory with TicketCollectionCellFactory;
}