package org.deltan.deltan.server;

import javax.annotation.Nonnull;

public interface ServerAddressFactory {

    ServerAddress create(@Nonnull String host, int port);
}
