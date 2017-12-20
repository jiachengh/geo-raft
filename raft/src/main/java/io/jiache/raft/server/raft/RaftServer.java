package io.jiache.raft.server.raft;

import io.jiache.raft.common.Managed;

import java.util.List;

public class RaftServer {
    private List<Managed> actions;
    private io.grpc.Server server;
    public static RaftServer newRaftServer(RaftContext initContext) {
        return null;
    }
}
