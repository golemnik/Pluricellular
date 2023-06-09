package com.golem.core.schemas.signature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Signature implements Serializable {
    private final String command;
    private final String description;
    private SignatureStatus status;
    private final List<String> patternSignature;
    private final List<String> commentSignature;
    private final List<String> mistakeInputSignature;

    public Signature (String command,
                      String description,
                      SignatureStatus status,
                      List<String> patternSignature,
                      List<String> commentSignature,
                      List<String> mistakeInputSignature)
    {
        this.command = command;
        this.description = description;
        this.status = status;
        this.patternSignature = patternSignature;
        this.commentSignature = commentSignature;
        this.mistakeInputSignature = mistakeInputSignature;
    }

    private Signature (Signature signature) {
        this.command = signature.command;
        this.description = signature.description;
        this.status = signature.status;
        this.patternSignature = signature.patternSignature;
        this.commentSignature = signature.commentSignature;
        this.mistakeInputSignature = signature.mistakeInputSignature;
    }


    public String command() {
        return command;
    }

    public String description() {
        return description;
    }

    public SignatureStatus status() {
        return status;
    }
    public void updateStatus (SignatureStatus status) {
        this.status = status;
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

    public Signature copy () {
        return new Signature(this);
    }

}
