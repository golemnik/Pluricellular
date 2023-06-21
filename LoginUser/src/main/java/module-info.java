import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.loginUser.LoginClientCellFactory;
import com.golem.loginUser.LoginClientCommandCell;
import com.golem.userModule.abstractions.AbstractUCellFactory;

module Pluricellular.LoginUser.main {
    requires ColonyCore;
    requires Pluricellular.UserModule.main;

    uses Cell;
    uses AbstractSystemCellFactory;

    provides Cell with LoginClientCommandCell;
    provides AbstractUCellFactory with LoginClientCellFactory;

}