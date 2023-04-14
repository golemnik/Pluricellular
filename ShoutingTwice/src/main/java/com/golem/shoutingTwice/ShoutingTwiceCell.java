package com.golem.shoutingTwice;

import com.golem.core.schemas.Cell;
import com.golem.shoutingCell.ShoutingCell;

public class ShoutingTwiceCell extends ShoutingCell implements Cell {
    private String shout = "WAAAAAAAAAAAAAAAAAAAAAAA!!!!";

    @Override
    public void activate() {
        System.out.println(shout);
        System.out.println(shout);
    }
    public void changeShout (String shout) {
        this.shout = shout;
    }
}
