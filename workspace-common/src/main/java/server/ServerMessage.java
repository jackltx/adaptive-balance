package server;

import java.util.concurrent.atomic.AtomicLong;

public class ServerMessage {

    private String url;

    private String host;

    private Integer maxThreads;

    private AtomicLong totalReceiveReq=new AtomicLong(0L);

    private AtomicLong totalHandleReq=new AtomicLong(0L);

    private int avail;

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    public void atomicAddReceive(long num) {
        totalReceiveReq.addAndGet(num);
    }

    public void atomicAddHandle(long num) {
        totalHandleReq.addAndGet(num);
    }

    public static ServerMessage buildServer() {
        return new ServerMessage();
    }

    public ServerMessage setUrl(String url) {
        this.url = url;
        return this;
    }

    public ServerMessage setHost(String host) {
        this.host =host;
        return this;
    }

    public AtomicLong getTotalReceiveReq() {
        return totalReceiveReq;
    }

    public AtomicLong getTotalHandleReq() {
        return totalHandleReq;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }
}
