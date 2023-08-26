package com.example.netgame2.packets;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerPacket implements Serializable
{
    public int x;
    public PlayerState ps;
    public int y;



    public PlayerPacket(int x, int y, PlayerState ps)
    {
//        this.num=num;
        this.x=x;
        this.y=y;
        this.ps=ps;
    }
}
