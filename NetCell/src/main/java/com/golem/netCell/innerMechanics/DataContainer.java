package com.golem.netCell.innerMechanics;

import java.io.Serializable;
import java.util.List;

public class DataContainer implements Serializable {
    public final List <String> data;
    public DataContainer (List<String> data) {
        this.data = data;
    }
}
