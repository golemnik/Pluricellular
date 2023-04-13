package com.golem.core.basicRealisation;

import com.golem.core.schemas.Cell;

public class CorruptedCell implements Cell {
    @Override
    public void activate() {
        System.out.println("Smthing went wrong... this cell is corrupted one.");
    }
}
