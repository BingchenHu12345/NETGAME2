package com.example.netgame2.packets;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable
{
    public int num;

    public ArrayList<Player> playerlist=new ArrayList<>();

    @Override
    public String toString() {
        return "Packet{" +
                "num=" + num +
                '}';
    }

    public Packet(ArrayList<Player> pl)
    {
//        this.num=num;
        this.playerlist=pl;
    }
    public Packet()
    {

    }
}

