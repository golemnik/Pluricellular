package com.golem.core.schemas.basicInterfaces;

import java.io.IOException;

public interface TerminalCell extends Cell {
    void terminalInit();
    void terminalCycle () throws IOException;
}
