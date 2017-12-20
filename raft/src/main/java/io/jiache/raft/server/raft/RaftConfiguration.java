package io.jiache.raft.server.raft;


import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RaftConfiguration {
    private final Duration heartBeatTime;
    private final Duration minTimeOut;
    private final Duration maxTimeOut;
    private final ReadConsistency readConsistency;
    private final Integer maxRetries;
    private final Duration retryDelay;
    private final Executor executor;

    private RaftConfiguration(
            Duration heartBeatTime,
            Duration minTimeOut,
            Duration maxTimeOut,
            ReadConsistency readConsistency,
            Integer maxRetries,
            Duration retryDelay,
            Executor executor) {
        this.heartBeatTime = heartBeatTime;
        this.minTimeOut = minTimeOut;
        this.maxTimeOut = maxTimeOut;
        this.readConsistency = readConsistency;
        this.maxRetries = maxRetries;
        this.retryDelay = retryDelay;
        this.executor = executor;
    }

    public Duration getHeartBeatTime() {
        return heartBeatTime;
    }

    public Duration getMinTimeOut() {
        return minTimeOut;
    }

    public Duration getMaxTimeOut() {
        return maxTimeOut;
    }

    public ReadConsistency getReadConsistency() {
        return readConsistency;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public Duration getRetryDelay() {
        return retryDelay;
    }

    public Executor getExecutor() {
        return executor;
    }

    public static class Builder implements io.jiache.raft.common.Builder<RaftConfiguration> {
        private Duration heartBeatTime = Duration.ofMillis(500L);
        private Duration minTimeOut = Duration.ofMillis(1000L);
        private Duration maxTimeOut = Duration.ofMillis(2000L);
        private ReadConsistency readConsistency = ReadConsistency.STRONG;
        private Integer maxRetries = 0;
        private Duration retryDelay = Duration.ofMillis(100);
        private Executor executor = Executors.newCachedThreadPool(); // TODO

        public void setHeartBeatTime(Duration heartBeatTime) {
            this.heartBeatTime = heartBeatTime;
        }

        public void setMinTimeOut(Duration minTimeOut) {
            this.minTimeOut = minTimeOut;
        }

        public void setMaxTimeOut(Duration maxTimeOut) {
            this.maxTimeOut = maxTimeOut;
        }

        public void setReadConsistency(ReadConsistency readConsistency) {
            this.readConsistency = readConsistency;
        }

        public void setMaxRetries(Integer maxRetries) {
            this.maxRetries = maxRetries;
        }

        public void setRetryDelay(Duration retryDelay) {
            this.retryDelay = retryDelay;
        }

        public void setExecutor(Executor executor) {
            this.executor = executor;
        }

        @Override
        public RaftConfiguration build() {
            return new RaftConfiguration(
                    heartBeatTime,
                    minTimeOut,
                    maxTimeOut,
                    readConsistency,
                    maxRetries,
                    retryDelay,
                    executor
            );
        }
    }
}
