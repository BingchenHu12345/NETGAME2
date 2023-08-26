package com.example.netgame2.packets;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable
{
    public int x,y,x1,y1;
    public int playerCount;

    public PlayerState player1State;
    public PlayerState player2State;

    public int ID;


    public Packet(int x,int y,int ID,PlayerState ps)
    {
        this.x=x;
        this.y=y;
        this.ID=ID;
        if(ID==0){
            this.player1State=ps;
        }
        else{
            this.player2State=ps;
        }

        playerCount=1;
    }
    public Packet(int x1,int y1,int x2,int y2,int ID,PlayerState ps)
    {
        this.x=x1;
        this.y=y1;
        this.x1=x2;
        this.y1=y2;
        this.ID=ID;
        if(ID==0){
            this.player1State=ps;
        }
        else{
            this.player2State=ps;
        }
        playerCount=2;
    }
    public Packet()
    {

    }

    @Override
    public String toString() {
        return "Packet{" +
                "x=" + x +
                ", y=" + y +
                ", x1=" + x1 +
                ", y1=" + y1 +
                ", playerCount=" + playerCount +
                '}';
    }
}

