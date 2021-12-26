package org.deltan.deltan.server;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class ServerAddressImpl implements ServerAddress {

    private final String host;
    private final int port;

    @AssistedInject
    public ServerAddressImpl(@Assisted String host, @Assisted int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
