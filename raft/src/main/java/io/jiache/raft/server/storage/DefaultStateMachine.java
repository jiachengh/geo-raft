package io.jiache.raft.server.storage;

import java.util.Map;

public class DefaultStateMachine implements StateMachine{
    private volatile Map<byte[], byte[]> state;
    private final Object lock = new Object();

    public DefaultStateMachine(Map<byte[], byte[]> state) {
        this.state = state;
    }

    @Override
    public void put(byte[] key, byte[] value) {
        synchronized (lock) {
            state.put(key, value);
        }
    }

    @Override
    public byte[] get(byte[] key) {
        return state.get(key);
    }

    @Override
    public void commit(Entry entry) {
        put(entry.getKey(), entry.getValue());
    }
}
