package com.golem.core.schemas.signature;

import com.golem.core.schemas.basicAbstractions.AbstractBroodMother;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.core.schemas.providedRealisations.CorruptedCommandCell;

import java.io.BufferedReader;
import java.io.IOException;
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
        Signature signature = ascf.getSignature();
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

    public static AbstractCommand consoleInputCycle (BufferedReader scanner, AbstractBroodMother broodMother, String cell) throws IOException {
        AbstractSystemCellFactory ascf = broodMother.getFactoryCommands().get(cell.split(" ")[0]);
        if (ascf == null) {
            return new CorruptedCommandCell("Unsupported command.");
        }
        Signature signature = ascf.getSignature();
        List<String> inputSignature = new ArrayList<>();
        String input = cell;
        for (int i = 0; i < signature.patternSignature().size(); i++) {
            if (i != 0) {
                CellPrinter.setMessage(signature.commentSignature().get(i));
                input = scanner.readLine();
            }
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

    public static AbstractCommand consoleInputIter (Scanner scanner, AbstractBroodMother broodMother, String cell) {
        AbstractSystemCellFactory ascf = broodMother.getFactoryCommands().get(cell.split(" ")[0]);
        if (ascf == null) {
            return new CorruptedCommandCell("Unsupported command.");
        }
        Signature signature = ascf.getSignature();
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
    public static List<Signature> signatureList (AbstractBroodMother broodMother, SignatureStatus type) {
        List<Signature> signatureList = new ArrayList<>();
        for (AbstractSystemCellFactory asf : broodMother.getFactoryCommands().values()) {
            if (asf.getSignature().status() == type) {
                signatureList.add(asf.getSignature());
            }
        }
        return signatureList;
    }
    public static List<Signature> signatureList (AbstractBroodMother broodMother, SignatureStatus status, SignatureStatus changedStatus) {
        List<Signature> signatureList = new ArrayList<>();
        Signature signature;
        for (AbstractSystemCellFactory asf : broodMother.getFactoryCommands().values()) {
//            System.out.println(asf.getSignature().command());
            if (asf.getSignature().status() == status) {
                signature = asf.getSignature().copy();
                signature.updateStatus(changedStatus);
                signatureList.add(signature);
//                System.out.println("\tsend");
            }
        }
        return signatureList;
    }
}
