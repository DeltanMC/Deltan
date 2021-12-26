package org.deltan.deltan.server;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(ServerAddress.class, ServerAddressImpl.class)
                .build(ServerAddressFactory.class));

        bind(Server.class).to(ServerImpl.class);
    }
}
