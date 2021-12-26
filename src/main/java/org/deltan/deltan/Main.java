package org.deltan.deltan;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger("Deltan");

    public static void main(String[] args) {
        LOGGER.info("Starting Deltan...");

        long startTime = System.currentTimeMillis();
        new Bootstrap().bootstrap();
        long endTime = System.currentTimeMillis();

        LOGGER.info(String.format("Started Deltan in %d ms", endTime - startTime));
    }
}
