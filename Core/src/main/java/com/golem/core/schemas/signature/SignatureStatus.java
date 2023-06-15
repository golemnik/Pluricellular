package com.golem.core.schemas.signature;

import java.io.Serializable;

public enum SignatureStatus implements Serializable {
    SYSTEM,
    CONNECTED,
    PROVIDED,
    FAKE
}
