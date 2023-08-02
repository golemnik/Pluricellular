import com.golem.clientCell.recipient.userfactories.AbstractUCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.login.LoginCommandCell;
import com.golem.login.LoginCellFactory;

module Pluricellular.UserLogin.main {
    requires ColonyCore;
    requires Pluricellular.ClientCell.main;

    uses Cell;
    uses AbstractUCellFactory;

    provides Cell with LoginCommandCell;
    provides AbstractUCellFactory with LoginCellFactory;

}