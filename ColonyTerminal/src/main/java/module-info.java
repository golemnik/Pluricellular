import com.golem.core.schemas.TerminalCell;
import com.golem.terminal.ColonyTerminalCell;
import com.golem.terminal.terminalCommands.commandFactories.EndCellFactory;
import com.golem.terminal.terminalCommands.commands.EndCellCommand;
import com.golem.terminal.terminalCommands.schemes.TerminalCommand;
import com.golem.terminal.terminalCommands.schemes.TerminalCommandFactory;

module Pluricellular.ColonyTerminal.main {
    requires ColonyCore;
    exports com.golem.terminal.terminalCommands.schemes;

    uses TerminalCommandFactory;
    uses TerminalCommand;

    provides TerminalCell with ColonyTerminalCell;
    provides TerminalCommandFactory with EndCellFactory;
    provides TerminalCommand with EndCellCommand;
}