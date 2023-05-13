package com.golem.netCell;

import com.golem.core.schemas.basicAbstractions.AbstractTerminal;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.netCell.recipient.Recipient;
import com.golem.netCell.transmitter.Transmitter;

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
    public void terminalCycle() {
        while (true) {
            switch (scanner.nextLine()) {
                case "server" -> new Transmitter().cycle(this);
                case "client" -> new Recipient().cycle(this);
            }
        }
    }
}
