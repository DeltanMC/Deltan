package org.deltan.deltan.net;

import com.google.inject.AbstractModule;

public class NetModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NetServer.class).to(NetServerImpl.class);
    }
}
