package com.golem.core.schemas.basicRealisation;

import com.golem.core.schemas.basicInterfaces.Cell;

public class BasicCell implements Cell {
    @Override
    public void activate() {
        System.out.println("This is Basic Cell activation.");
    }

}
