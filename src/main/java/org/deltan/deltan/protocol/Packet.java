package org.deltan.deltan.protocol;

public abstract class Packet {

    private final int id;

    public Packet(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
