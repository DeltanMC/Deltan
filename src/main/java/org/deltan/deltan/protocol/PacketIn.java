package org.deltan.deltan.protocol;

import io.netty.buffer.ByteBuf;

public abstract class PacketIn extends Packet {

    public PacketIn(int id) {
        super(id);
    }

    public abstract void read(ByteBuf in);
}
