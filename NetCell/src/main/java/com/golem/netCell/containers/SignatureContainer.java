package com.golem.netCell.containers;

import com.golem.core.schemas.basicAbstractions.Signature;

import java.io.Serializable;
import java.util.List;

public class SignatureContainer extends BaseContainer implements Serializable {
    private List<Signature> signatures;
    public SignatureContainer (ContainerType type) {
        super(type);
    }

    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }
}
