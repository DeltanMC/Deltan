package org.deltan.deltan.server;

public interface ServerAddress {

    String getHost();

    int getPort();

    default String getIp() {
        return String.format("%s:%d", this.getHost(), this.getPort());
    }
}
