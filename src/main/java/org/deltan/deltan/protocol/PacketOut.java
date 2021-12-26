package org.deltan.deltan.protocol;

import io.netty.buffer.ByteBuf;

public abstract class PacketOut extends Packet {

    public PacketOut(int id) {
        super(id);
    }

    public abstract void write(ByteBuf out);
}
