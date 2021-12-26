package org.deltan.deltan.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.deltan.deltan.server.ServerAddress;

import javax.inject.Inject;
import java.util.logging.Logger;

public class NetServerImpl implements NetServer {

    private static final Logger LOGGER = Logger.getLogger("Deltan");

    private static final int MAXIMUM_QUEUE_LENGTH = 128;

    private final NetChannelInitializer channelInitializer;

    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;

    @Inject
    public NetServerImpl(NetChannelInitializer channelInitializer) {
        this.channelInitializer = channelInitializer;

        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
    }

    @Override
    public void start(ServerAddress address) {
        LOGGER.info(String.format("Starting netty server on %s...", address.getIp()));

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(this.bossGroup, this.workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(this.channelInitializer)
                .option(ChannelOption.SO_BACKLOG, MAXIMUM_QUEUE_LENGTH)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.bind(address.getHost(), address.getPort());

        LOGGER.info("Netty server started successfully !");
    }

    @Override
    public void shutdown() {
        LOGGER.info("Shutdown netty server...");

        this.bossGroup.shutdownGracefully();
        this.workerGroup.shutdownGracefully();

        LOGGER.info("Netty server shutdown successfully !");
    }
}
