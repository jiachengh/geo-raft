package io.jiache.raft.server.agent;

import io.jiache.raft.common.Managed;

public class AgentAction implements Managed {
    @Override
    public void start() {
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void stop() {
    }
}
