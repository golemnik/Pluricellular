package com.golem.netCell.innerMechanics;

import com.golem.core.schemas.basicAbstractions.AbstractTerminal;

public interface NetConnection {
    void cycle (AbstractTerminal terminal);
}
