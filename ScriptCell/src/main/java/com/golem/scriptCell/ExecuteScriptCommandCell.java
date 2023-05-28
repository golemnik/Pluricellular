package com.golem.scriptCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.basicInterfaces.Cell;

import java.util.List;

public class ExecuteScriptCommandCell extends AbstractCommand implements Cell {
    private List <String> commandsQueue;
    private BroodMotherCell broodMother;

    public void addBroodMother (BroodMotherCell broodMother) {
        this.broodMother = broodMother;
    }
    @Override
    public void activate() {
        String input = "";
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        commandsQueue = ScriptInputer.readScript(signature.get(0).split(" ")[0]);
        return this;
    }
}
