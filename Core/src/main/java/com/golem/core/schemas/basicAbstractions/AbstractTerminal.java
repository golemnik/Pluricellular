package com.golem.core.schemas.basicAbstractions;

import com.golem.core.schemas.basicInterfaces.TerminalCell;
import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractTerminal extends AbstractExtendedICellCore implements TerminalCell {

    public abstract int priority();

    @Override
    public void activate() {
        terminalInit();
        state = State.FIRST_CONNECTION;
        do {
            try {
                terminalCycle();
                state = State.DONE;
            }
            catch (IOException e) {
                state = nextState(state, e);
                if (state == State.DONE) {
                    CellPrinter.setMessage("Connection not found. Closing terminal.");
                }
                else {
                    CellPrinter.setMessage("Connection lost. Reconnecting.");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ine) {
                        CellPrinter.setMessage("Sleep interrupted...");
                        state = State.DONE;
                    }
                }
            }
        } while (state!=State.DONE);
    }

    @Override
    public abstract void terminalInit();

    @Override
    public abstract void terminalCycle() throws IOException;

    public static List<AbstractTerminal> getCellTerminals(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, AbstractTerminal.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    enum State { FIRST_CONNECTION, RECONNECTION, DONE }
    private State state;
    private State nextState(State state, IOException e) {
        switch (state) {
            case FIRST_CONNECTION:
                return e instanceof ConnectException ? State.DONE : State.RECONNECTION;
            case RECONNECTION:
            case DONE:
                return state;
            default:
                return State.DONE;
        }
    }

}
