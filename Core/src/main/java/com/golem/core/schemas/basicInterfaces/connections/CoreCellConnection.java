package com.golem.core.schemas.basicInterfaces.connections;

import com.golem.core.coreCell.CoreCell;

public interface CoreCellConnection {
    void addCore (CoreCell coreCell);
    CoreCell getCoreCell ();
}
