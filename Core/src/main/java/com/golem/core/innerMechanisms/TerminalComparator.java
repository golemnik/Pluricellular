package com.golem.core.innerMechanisms;

import com.golem.core.schemas.abstracts.AbstractTerminal;

import java.util.Comparator;

public class TerminalComparator implements Comparator<AbstractTerminal> {
    @Override
    public int compare(AbstractTerminal t1, AbstractTerminal t2) {
        return t1.priority() - t2.priority();
    }
}
