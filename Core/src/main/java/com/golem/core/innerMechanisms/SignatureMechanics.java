package com.golem.core.innerMechanisms;

import com.golem.core.schemas.basicAbstractions.AbstractBroodMother;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SignatureMechanics {
    public static AbstractCommand consoleInputCycle (Scanner scanner, AbstractBroodMother broodMother, String cell) {
        Signature signature = broodMother.getFactoryCommands().get(cell).getSignature();
        if (signature == null) {
            return new
        }
        List<String> inputSignature = new ArrayList<>();
        String input;
        for (int i = 0; i < signature.patternSignature().size(); i++) {
            CellPrinter.setMessage(signature.commentSignature().get(i));
            if (!Pattern.matches(signature.patternSignature().get(i), input = scanner.nextLine())) {
                CellPrinter.setMessage(signature.mistakeInputSignature().get(i));
                i--;
                continue;
            }
            inputSignature.add(input);
        }
        return inputSignature;
    }
}
