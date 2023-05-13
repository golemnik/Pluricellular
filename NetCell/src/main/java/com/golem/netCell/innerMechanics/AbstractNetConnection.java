package com.golem.netCell.innerMechanics;

import com.golem.core.schemas.providedRealisations.CellPrinter;

public abstract class AbstractNetConnection implements NetConnection{
    protected static  <T> T safeConvert (Object object) {
        T dataContainer = null;
        try {
            dataContainer = (T) object;
        }
        catch (Exception e) {
            CellPrinter.setMessage("Container corrupted.");
        }
        return dataContainer;
    }


}
