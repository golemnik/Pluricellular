import com.golem.clientCell.recipient.Recipient;
import com.golem.netCell.innerMechanics.NetConnection;

module Pluricellular.ClientCell.main {
    requires ColonyCore;
    requires Pluricellular.NetCell.main;

    exports com.golem.clientCell.recipient.user;

    provides NetConnection with Recipient;
}