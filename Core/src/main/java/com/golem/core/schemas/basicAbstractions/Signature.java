package com.golem.core.schemas.basicAbstractions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Signature(List<String> patternSignature, List<String> commentSignature, List<String> mistakeInputSignature)
        implements Serializable {
    public Signature(List<String> patternSignature, List<String> commentSignature, List<String> mistakeInputSignature) {
        this.patternSignature = new ArrayList<>(patternSignature);
        this.commentSignature = new ArrayList<>(commentSignature);
        this.mistakeInputSignature = new ArrayList<>(mistakeInputSignature);
    }

    @Override
    public List<String> patternSignature() {
        return new ArrayList<>(patternSignature);
    }

    @Override
    public List<String> commentSignature() {
        return new ArrayList<>(commentSignature);
    }

    @Override
    public List<String> mistakeInputSignature() {
        return new ArrayList<>(mistakeInputSignature);
    }
}
