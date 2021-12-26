package org.deltan.deltan.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.deltan.deltan.protocol.PacketOut;

public class PacketEncoder extends MessageToByteEncoder<PacketOut> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PacketOut msg, ByteBuf out) throws Exception {

    }
}
