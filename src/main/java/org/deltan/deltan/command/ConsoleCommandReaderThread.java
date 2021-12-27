package org.deltan.deltan.command;

import org.deltan.deltan.server.Server;

import javax.inject.Inject;
import java.io.Console;

public class ConsoleCommandReaderThread extends Thread {

    private static final char PROMPT = '>';

    private final Console console;
    private final CommandDispatcher commandDispatcher;
    private final Server server;

    @Inject
    public ConsoleCommandReaderThread(Console console,
                                      CommandDispatcher commandDispatcher,
                                      Server server) {
        super("Console");

        this.console = console;
        this.commandDispatcher = commandDispatcher;
        this.server = server;
    }

    @Override
    public void run() {
        while (this.server.isRunning()) {
            this.console.printf("%c ", PROMPT);
            String command = this.console.readLine();

            this.commandDispatcher.dispatch(command);
        }
    }
}
