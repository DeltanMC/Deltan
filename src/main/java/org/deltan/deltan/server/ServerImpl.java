package org.deltan.deltan.server;

import org.deltan.deltan.net.NetServer;

import javax.inject.Inject;
import java.util.logging.Logger;

public class ServerImpl implements Server {

    private static final Logger LOGGER = Logger.getLogger("Deltan");

    private final ServerAddressFactory addressFactory;
    private final NetServer netServer;

    private boolean running;

    @Inject
    public ServerImpl(ServerAddressFactory addressFactory, NetServer netServer) {
        this.addressFactory = addressFactory;
        this.netServer = netServer;

        this.running = false;
    }

    @Override
    public void start(String host, int port) {
        ServerAddress address = this.addressFactory.create(host, port);
        this.netServer.start(address);

        this.running = true;
    }

    @Override
    public void stop() {
        this.running = false;

        LOGGER.info("Stopping server...");

        this.netServer.shutdown();

        LOGGER.info("Server stopped successfully !");
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }
}
