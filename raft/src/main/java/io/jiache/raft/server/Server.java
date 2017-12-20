package io.jiache.raft.server;

import io.jiache.raft.common.Managed;

public interface Server extends Managed {
    String CLIENT_HOST = "127.0.0.1";
    Integer CLIENT_PORT = 9100;
    String PEER_HOST = "127.0.0.1";
    Integer PEER_PORT = 9400;

}