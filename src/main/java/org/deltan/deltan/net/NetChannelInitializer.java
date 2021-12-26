package org.deltan.deltan.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.deltan.deltan.net.pipeline.PacketDecoder;
import org.deltan.deltan.net.pipeline.PacketEncoder;

public class NetChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                .addLast(new PacketDecoder())
                .addLast(new PacketEncoder());
    }
}
