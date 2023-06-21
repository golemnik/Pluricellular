import com.golem.userModule.abstractions.UserQueen;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;

module Pluricellular.UserModule.main {
    requires ColonyCore;
    requires Pluricellular.ClientCell.main;

    exports com.golem.userModule.abstractions;
    exports com.golem.userModule;

    uses AbstractQueenCell;

    provides AbstractQueenCell with UserQueen;

}