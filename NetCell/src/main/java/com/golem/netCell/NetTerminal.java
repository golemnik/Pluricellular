package com.golem.netCell;

import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.innerMechanics.NetConnection;

import java.io.IOException;
import java.util.Scanner;

public class NetTerminal extends AbstractTerminal {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public int priority() {
        return 10;
    }

    @Override
    public void terminalInit() {
        CellPrinter.setMessage("Net terminal in use...");
    }

    @Override
    public void terminalCycle() throws IOException {
        try {
            NetConnection.getConnector(CellLayer.getLayer()).get(0).cycle(this);
        }
        catch (NullPointerException np) {
            CellPrinter.setMessage("Net terminal must be provided with at least one net connector.");
            System.exit(0);
        }
        catch (IOException ioe) {
            throw ioe;
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
        }
    }
}
