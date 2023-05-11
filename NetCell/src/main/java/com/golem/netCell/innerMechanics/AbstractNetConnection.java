package com.golem.netCell.innerMechanics;

import com.golem.core.schemas.providedRealisations.CellPrinter;

public abstract class AbstractNetConnection implements NetConnection{
    protected DataContainer safeConvert (Object object) {
        DataContainer dataContainer = null;
        try {
            dataContainer = (DataContainer) object;
        }
        catch (Exception e) {
            CellPrinter.setMessage("Data container corrupted.");
        }
        return dataContainer;
    }
}
