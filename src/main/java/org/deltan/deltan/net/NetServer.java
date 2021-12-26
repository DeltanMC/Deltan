package org.deltan.deltan.net;

import org.deltan.deltan.server.ServerAddress;

public interface NetServer {

    void start(ServerAddress address);

    void shutdown();
}
