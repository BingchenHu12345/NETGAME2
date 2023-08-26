package com.example.netgame2.packets;

import java.io.Serializable;

public class Player implements Serializable
{
    public int x;
    public int y;

    public PlayerState ps;

    public Player(int x, int y,PlayerState ps)
    {
        this.x=x;
        this.y=y;
        this.ps=ps;
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
