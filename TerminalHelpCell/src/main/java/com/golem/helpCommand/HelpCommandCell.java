package com.golem.helpCommand;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;

public class HelpCommandCell implements Cell {
    private CellBroodMother broodMother;

    public HelpCommandCell (){}

    public void addBroodMother (CellBroodMother broodMother) {
        this.broodMother = broodMother;
    }
    @Override
    public void activate() {
        System.out.println("Available commands:");
        for (String s : broodMother.getFactoryCommands().keySet()) {
            System.out.println("\"" + s + "\" - " + broodMother.getFactoryCommands().get(s).commandDescription());
        }
    }
}
