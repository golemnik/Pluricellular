package com.golem.helpCommand;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;

import java.util.ArrayList;
import java.util.List;

public class HelpCommandCell extends AbstractCommand implements Cell {
    private BroodMotherCell broodMother;

    public HelpCommandCell (){}

    public void addBroodMother (BroodMotherCell broodMother) {
        this.broodMother = broodMother;
    }
    @Override
    public void activate() {
        List<String> answer = new ArrayList<>();
        answer.add("Available commands:");
        for (String s : broodMother.getFactoryCommands().keySet()) {
            answer.add("\"" + s + "\" - " + broodMother.getFactoryCommands().get(s).commandDescription());
        }
        setAnswer(answer);
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        return this;
    }
}
