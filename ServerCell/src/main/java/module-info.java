import com.golem.netCell.innerMechanics.NetConnection;
import com.golem.serverCell.transmitter.Transmitter;

module Pluricellular.ServerCell.main {
    requires ColonyCore;
    requires Pluricellular.NetCell.main;
    requires org.apache.logging.log4j;

    provides NetConnection with Transmitter;

}