import com.golem.database.DatabaseManager;
import com.golem.ticketCell.access.AbstractAccess;

module Pluricellular.TicketDatabaseAccess.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;
    requires java.sql;

    uses AbstractAccess;

    provides AbstractAccess with DatabaseManager;
}