package io.jiache.raft.common;

import java.util.concurrent.CompletableFuture;

public interface Managed {
    void start();
    boolean isRunning();
    void stop();
}
