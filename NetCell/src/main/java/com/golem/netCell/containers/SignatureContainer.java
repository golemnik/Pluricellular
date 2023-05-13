package com.golem.netCell.containers;

import com.golem.core.schemas.basicAbstractions.Signature;

import java.io.Serializable;
import java.util.List;

public class SignatureContainer extends BaseContainer implements Serializable {
    public final List<Signature> signatures;
    public SignatureContainer (List<Signature> data) {
        this.signatures = data;
    }
}
