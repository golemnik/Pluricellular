package com.golem.core.schemas.basicRealisation.coreCommands;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicAbstractions.Signature;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;

import java.util.ArrayList;
import java.util.List;

public class ReloadGenomeCellFactory extends AbstractSystemCellFactory {
    public ReloadGenomeCellFactory() {
        super(new Signature(
                new ArrayList<>(List.of("reload_genome")),
                new ArrayList<>(List.of("")),
                new ArrayList<>(List.of("unsupported arguments")),
                "update genome to find new genes can be used."));
    }
    @Override
    public String creationCommand() {
        return "reload_genome";
    }

    @Override
    public AbstractCommand create(List<String> signature) {
        ReloadGenomeCommandCell cell = new ReloadGenomeCommandCell();
        cell.setQueen(getCoreCell().getQueenConnections());
        cell.setBroodMother(getBroodMother());
        return cell.useSignature(signature);
    }
}
