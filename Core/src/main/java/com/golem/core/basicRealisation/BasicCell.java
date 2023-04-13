package com.golem.core.basicRealisation;

import com.golem.core.schemas.Cell;

public class BasicCell implements Cell {
    @Override
    public void activate() {
        System.out.println("This is Basic Cell activation.");
    }

}
