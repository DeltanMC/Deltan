package org.deltan.deltan.server;

import org.deltan.deltan.net.NetServer;

import javax.inject.Inject;
import java.util.Optional;
import java.util.logging.Logger;

public class ServerImpl implements Server {

    private static final Logger LOGGER = Logger.getLogger("Deltan");

    private final NetServer netServer;
    private final ServerAddressFactory serverAddressFactory;

    @Inject
    public ServerImpl(NetServer netServer, ServerAddressFactory serverAddressFactory) {
        this.netServer = netServer;
        this.serverAddressFactory = serverAddressFactory;
    }

    @Override
    public void start(String host, int port) {
        LOGGER.info("Starting server...");

        ServerAddress address = this.serverAddressFactory.create(host, port);
        this.netServer.start(address);

        LOGGER.info("Server started successfully !");
    }

    @Override
    public void stop() {
        LOGGER.info("Stopping server...");

        Optional.ofNullable(this.netServer)
                .orElseThrow(() -> new IllegalStateException("Netty server has not been initialized"))
                .shutdown();

        LOGGER.info("Server stopped successfully !");
    }
}
