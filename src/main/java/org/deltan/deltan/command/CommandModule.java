package org.deltan.deltan.command;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.io.Console;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandDispatcher.class).to(CommandDispatcherImpl.class);
    }

    @Provides
    public Console provideConsole() {
        return System.console();
    }
}
