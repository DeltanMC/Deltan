package org.deltan.deltan.server;

public interface Server {

    void start(String host, int port);

    void stop();

    boolean isRunning();
}
