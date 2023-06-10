package com.example.netgame2.packets;

import java.io.Serializable;

public class Packet implements Serializable
{
    public int num;

    @Override
    public String toString() {
        return "Packet{" +
                "num=" + num +
                '}';
    }

    public Packet(int num)
    {
        this.num=num;
    }
}

