package org.deltan.deltan;

import com.google.inject.AbstractModule;
import org.deltan.deltan.command.CommandModule;
import org.deltan.deltan.net.NetModule;
import org.deltan.deltan.server.ServerModule;

public class DeltanModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new CommandModule());
        install(new NetModule());
        install(new ServerModule());
    }
}
