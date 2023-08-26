package com.example.netgame2.packets;

import java.io.Serializable;

public record PlayerState(boolean isAt)implements Serializable {
    public PlayerState(boolean isAt){
        this.isAt=isAt;
    }
}
