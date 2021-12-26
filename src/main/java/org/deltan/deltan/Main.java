package org.deltan.deltan;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.apache.commons.cli.*;
import org.deltan.deltan.server.Server;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger("Deltan");

    private static final Stage DEVELOPMENT_STAGE = Stage.DEVELOPMENT;

    private static final String DEFAULT_SERVER_HOST = "localhost";
    private static final int DEFAULT_SERVER_PORT = 25565;

    public static void main(String[] args) {
        Options options = new Options();

        Option hostOption = new Option("h", "host", true, "server host");
        options.addOption(hostOption);

        Option portOption = new Option("p", "port", true, "server port");
        options.addOption(portOption);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);

            String host = Optional.ofNullable(commandLine.getOptionValue(hostOption))
                    .orElse(DEFAULT_SERVER_HOST);

            int port = Optional.ofNullable(commandLine.getOptionValue(portOption))
                    .map(Integer::parseInt)
                    .orElse(DEFAULT_SERVER_PORT);

            long startTime = System.currentTimeMillis();

            Injector injector = Guice.createInjector(DEVELOPMENT_STAGE, new DeltanModule());

            Server server = injector.getInstance(Server.class);
            server.start(host, port);

            long endTime = System.currentTimeMillis();

            LOGGER.info(String.format("Deltan took %d ms to start", endTime - startTime));

            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        } catch (ParseException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE,
                    """
                            An error occurred while analysing command line options
                            Please refer to the exception bellow for further details
                            """
            );

            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    """
                            An error occurred while starting the server
                            Please refer to the exception bellow for further details
                            """
            );

            e.printStackTrace();
        }
    }
}
