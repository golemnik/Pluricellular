import com.golem.core.schemas.TerminalCell;
import com.golem.terminal.ColonyTerminalCell;

module Pluricellular.ColonyTerminal.main {
    uses com.golem.terminal.terminalCommands.commands.TerminalCommandFactory;
    requires ColonyCore;

    provides TerminalCell with ColonyTerminalCell;
}