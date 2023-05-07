package com.golem.core.schemas;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface TerminalCell extends Cell {
    void terminalInit();
    void terminalCycle ();
}
