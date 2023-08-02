import com.golem.clientCell.recipient.Recipient;
import com.golem.clientCell.recipient.userfactories.AbstractUCellFactory;
import com.golem.clientCell.recipient.userfactories.UserQueen;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.netCell.innerMechanics.NetConnection;

module Pluricellular.ClientCell.main {
    requires ColonyCore;
    requires Pluricellular.NetCell.main;

    exports com.golem.clientCell.recipient.user;
    exports com.golem.clientCell.recipient.userfactories;

    uses AbstractQueenCell;
    uses AbstractUCellFactory;

    provides NetConnection with Recipient;
    provides AbstractQueenCell with UserQueen;
}