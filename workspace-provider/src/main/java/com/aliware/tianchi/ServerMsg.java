package com.aliware.tianchi;

public class ServerMsg {
    private String name;
    private static int avail;

    public ServerMsg(String name) {
        this.name = name;
    }

    public static int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

}
