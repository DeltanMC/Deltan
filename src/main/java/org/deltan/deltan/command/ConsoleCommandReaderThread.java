package org.deltan.deltan.command;

import javax.inject.Inject;
import java.io.Console;

public class ConsoleCommandReaderThread extends Thread {

    private static final char PROMPT = '>';

    private final Console console;
    private final CommandDispatcher commandDispatcher;

    private boolean isRunning;

    @Inject
    public ConsoleCommandReaderThread(Console console, CommandDispatcher commandDispatcher) {
        super("Console");

        this.console = console;
        this.commandDispatcher = commandDispatcher;
    }

    @Override
    public synchronized void start() {
        super.start();

        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.printf("%c ", PROMPT);
            String command = this.console.readLine();

            this.commandDispatcher.dispatch(command);
        }
    }

    public void shutdown() {
        if (!this.isRunning) {
            throw new IllegalStateException("The Console Thread is already shutdown");
        }

        this.isRunning = false;
    }
}
