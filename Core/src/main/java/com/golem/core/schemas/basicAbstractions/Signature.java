package com.golem.core.schemas.basicAbstractions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Signature(List<String> patternSignature, List<String> commentSignature, List<String> mistakeInputSignature,
                        String description)
        implements Serializable {
    public Signature(List<String> patternSignature, List<String> commentSignature, List<String> mistakeInputSignature, String description) {
        this.patternSignature = new ArrayList<>(patternSignature);
        this.commentSignature = new ArrayList<>(commentSignature);
        this.mistakeInputSignature = new ArrayList<>(mistakeInputSignature);
        this.description = description;
    }

    public List<String> patternSignature() {
        return new ArrayList<>(patternSignature);
    }

    public List<String> commentSignature() {
        return new ArrayList<>(commentSignature);
    }

    public List<String> mistakeInputSignature() {
        return new ArrayList<>(mistakeInputSignature);
    }
    public String commandDescription () {
        return description;
    }
}
