package com.golem.core.innerMechanisms;

import com.golem.core.schemas.basicAbstractions.AbstractBroodMother;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SignatureMechanics {
    public static AbstractCommand consoleInputCycle (Scanner scanner, AbstractBroodMother broodMother, String cell) {
        AbstractSystemCellFactory ascf = broodMother.getFactoryCommands().get(cell.split(" ")[0]);
        if (ascf == null) {
            return new CorruptedCommandCell("Unsupported command.");
        }
        Signature signature = broodMother.getFactoryCommands().get(cell.split(" ")[0]).getSignature();
        List<String> inputSignature = new ArrayList<>();
        String input = cell;
        for (int i = 0; i < signature.patternSignature().size(); i++) {
            if (i != 0) {
                input = scanner.nextLine();
            }
            CellPrinter.setMessage(signature.commentSignature().get(i));
            if (!Pattern.matches(signature.patternSignature().get(i), input)) {
                if (i == 0) {
                    return new CorruptedCommandCell(signature.mistakeInputSignature().get(i));
                }
                CellPrinter.setMessage(signature.mistakeInputSignature().get(i));
                i--;
                continue;
            }
            inputSignature.add(input);
        }
        return broodMother.createCell(cell.split(" ")[0], inputSignature);
    }

    public static AbstractCommand createWithSignature (String cell, List<String> inputSignature, AbstractBroodMother broodMother) {
        return broodMother.createCell(cell.split(" ")[0], inputSignature);
    }

    public static List<Signature> signatureList (AbstractBroodMother broodMother) {
        List<Signature> signatureList = new ArrayList<>();
        for (AbstractSystemCellFactory ascf : broodMother.getFactoryCommands().values()) {
            signatureList.add(ascf.getSignature());
        }
        return signatureList;
    }
}
