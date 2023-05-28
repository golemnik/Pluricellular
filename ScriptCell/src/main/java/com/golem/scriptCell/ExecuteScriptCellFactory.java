package com.golem.scriptCell;

import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.signature.Signature;
import com.golem.core.schemas.signature.SignatureStatus;

import java.util.ArrayList;
import java.util.List;

public class ExecuteScriptCellFactory extends AbstractCellFactory {
    public ExecuteScriptCellFactory() {
        super(new Signature(
                "execute_script",
                "open txt file and try to execute every single command from the list.",
                SignatureStatus.SYSTEM,
                new ArrayList<>(List.of("execute_script .*\\.txt")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments"))));
    }
    @Override
    public AbstractCommand create(List<String> signature) {
        ExecuteScriptCommandCell cell = new ExecuteScriptCommandCell();
        cell.addBroodMother(getBroodMother());
        return cell.useSignature(signature);
    }
}
