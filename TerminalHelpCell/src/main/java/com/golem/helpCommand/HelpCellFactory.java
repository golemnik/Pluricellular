package com.golem.helpCommand;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class HelpCellFactory extends AbstractCellFactory {
    public HelpCellFactory () {
        super(new Signature(
                "help",
                "show commands and their descriptions.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("show")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }
    @Override
    public AbstractCommand create(List<String> signature) {
        HelpCommandCell cell = new HelpCommandCell();
        cell.addBroodMother(getBroodMother());
        return cell.useSignature(signature);
    }
}
