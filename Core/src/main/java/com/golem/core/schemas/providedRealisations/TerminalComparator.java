package com.golem.core.schemas.providedRealisations;

import com.golem.core.schemas.basicAbstractions.AbstractTerminal;

import java.util.Comparator;

public class TerminalComparator implements Comparator<AbstractTerminal> {
    @Override
    public int compare(AbstractTerminal t1, AbstractTerminal t2) {
        return t2.priority() - t1.priority();
    }
}
