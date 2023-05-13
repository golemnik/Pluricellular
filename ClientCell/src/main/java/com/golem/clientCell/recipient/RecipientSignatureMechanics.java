package com.golem.clientCell.recipient;

import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;

import java.util.*;
import java.util.regex.Pattern;

public class RecipientSignatureMechanics {
    private Map<String, Signature> signatureMap = new HashMap<>();
    public RecipientSignatureMechanics () {
    }
    public void updateSignatureMap (List<Signature> signatureList) {
        signatureMap.clear();
        signatureList.forEach(x -> signatureMap.put(x.patternSignature().get(0), x));
    }

    public List<String> signatureToSendCycle (Scanner scanner) {
        List<String> inputSignature = new ArrayList<>();
        Signature signature;
        while (true) {
            CellPrinter.setMessage("Input command:...");
            String input = scanner.nextLine();
            if ((signature = signatureMap.get(input.split(" ")[0])) == null) {
                CellPrinter.setMessage("Command is not exist.");
                continue;
            }
            for (int i = 0; i < signature.patternSignature().size(); i++) {
                if (i != 0) {
                    input = scanner.nextLine();
                }
                CellPrinter.setMessage(signature.commentSignature().get(i));
                if (!Pattern.matches(signature.patternSignature().get(i), input)) {
                    if (i == 0) {
                        CellPrinter.setMessage(signature.mistakeInputSignature().get(i));
                        break;
                    }
                    CellPrinter.setMessage(signature.mistakeInputSignature().get(i));
                    i--;
                    continue;
                }
                inputSignature.add(input);
            }
            return inputSignature;
        }
    }

    public Map<String, Signature> getSignatureMap() {
        return signatureMap;
    }
}
