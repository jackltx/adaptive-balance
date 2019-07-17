package server;

/**
 * 当前服务器快照信息
 */
public class Snapshot {

    private long snapshotTime;

    private String url;

    private String host;

    private Integer maxThreads;

    private Integer availableThreads;

    public long getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(long snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }

    public Integer getAvailableThreads() {
        return availableThreads;
    }

    public void setAvailableThreads(Integer availableThreads) {
        this.availableThreads = availableThreads;
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "snapshotTime=" + snapshotTime +
                ", url='" + url + '\'' +
                ", host='" + host + '\'' +
                ", maxThreads=" + maxThreads +
                ", availableThreads=" + availableThreads +
                '}';
    }
}
