package com.golem.shoutingCell;

import com.golem.core.schemas.Cell;
public class ShoutingCell implements Cell {
    private String shout = "WAAAAAAAAAAAAAAAAAAAAAAA!!!!";
    @Override
    public void activate() {
        System.out.println(shout);
    }
    public void changeShout (String shout) {
        this.shout = shout;
    }
}
