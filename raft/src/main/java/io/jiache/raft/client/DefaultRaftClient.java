package io.jiache.raft.client;

import io.jiache.raft.common.Address;
import io.jiache.raft.rpc.client.ClientServiceGrpc;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DefaultRaftClient implements RaftClient{
    private Map<Integer, Address> cluster;
    private volatile State state = State.CLOSED;
    private final Duration timeout;
    private final AddressSelector addressSelector;
    private ClientServiceGrpc.ClientServiceFutureStub stub;

    DefaultRaftClient(Map<Integer, Address> cluster, Duration timeout, AddressSelector addressSelector) {
        this.cluster = cluster;
        this.timeout = timeout;
        this.addressSelector = addressSelector;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public CompletableFuture<Byte[]> get(Byte[] key) {
        assert state() == State.CONNECTED;

        return null;
    }

    @Override
    public CompletableFuture<Void> put(Byte[] key, Byte[] value) {
        assert state() == State.CONNECTED;
        return null;
    }

    @Override
    public synchronized CompletableFuture<RaftClient> connect() {
        if (state() == State.CONNECTED) {
            return CompletableFuture.completedFuture(this);
        }

        return null;
    }

    @Override
    public synchronized CompletableFuture<Void> close() {
        return null;
    }
}
