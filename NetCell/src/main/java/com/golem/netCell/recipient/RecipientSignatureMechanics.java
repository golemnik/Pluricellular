package com.golem.netCell.recipient;

import com.golem.core.schemas.basicAbstractions.Signature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipientSignatureMechanics {
    private Map<String, Signature> signatureMap = new HashMap<>();
    public RecipientSignatureMechanics () {
    }
    public void updateSignatureMap (List<Signature> signatureList) {
        signatureMap.clear();
        signatureList.forEach(x -> signatureMap.put(x.patternSignature().get(0), x));
    }

    public Map<String, Signature> getSignatureMap() {
        return signatureMap;
    }
}
