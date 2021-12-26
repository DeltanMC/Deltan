package org.deltan.deltan.server;

import org.deltan.deltan.command.ConsoleCommandReaderThread;
import org.deltan.deltan.net.NetServer;

import javax.inject.Inject;
import java.util.logging.Logger;

public class ServerImpl implements Server {

    private static final Logger LOGGER = Logger.getLogger("Deltan");

    private final NetServer netServer;
    private final ServerAddressFactory serverAddressFactory;
    private final ConsoleCommandReaderThread consoleCommandReaderThread;

    @Inject
    public ServerImpl(NetServer netServer,
                      ServerAddressFactory serverAddressFactory,
                      ConsoleCommandReaderThread consoleCommandReaderThread) {
        this.netServer = netServer;
        this.serverAddressFactory = serverAddressFactory;
        this.consoleCommandReaderThread = consoleCommandReaderThread;
    }

    @Override
    public void start(String host, int port) {
        LOGGER.info("Starting server...");
        long startTime = System.currentTimeMillis();

        ServerAddress address = this.serverAddressFactory.create(host, port);
        this.netServer.start(address);

        long endTime = System.currentTimeMillis();
        LOGGER.info(String.format("Deltan took %d ms to start", endTime - startTime));

        this.consoleCommandReaderThread.start();
    }

    @Override
    public void stop() {
        LOGGER.info("Stopping server...");

        this.netServer.shutdown();

        this.consoleCommandReaderThread.shutdown();

        LOGGER.info("Server stopped successfully !");
    }
}
