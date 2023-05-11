package com.golem.helpCommand;

import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;

public class HelpCommandCell implements Cell {
    private BroodMotherCell broodMother;

    public HelpCommandCell (){}

    public void addBroodMother (BroodMotherCell broodMother) {
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
