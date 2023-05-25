package com.golem.clientCell.recipient;

import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.util.*;
import java.util.regex.Pattern;

public class RecipientSignatureMechanisms {
    private Map<String, Signature> signatureMap = new HashMap<>();
    public RecipientSignatureMechanisms() {
    }
    public void updateSignatureMap (List<Signature> signatureList) {
        signatureList.forEach(x -> signatureMap.put(x.command(), x));
    }

    public List<String> signatureToSendCycle (Scanner scanner) {
        List<String> inputSignature = new ArrayList<>();
        Signature signature;
        while (true) {
            CellPrinter.setMessage("Input command:...");
            String input = scanner.nextLine();
            if (input.equals("\\terminal commands")) {
                CellPrinter.setMessage(signatureMap.keySet().toString());
                continue;
            }
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
