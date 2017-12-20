package io.jiache.raft.server.storage;

import io.jiache.raft.common.Builder;

import java.util.HashMap;
import java.util.Map;

public interface StateMachine {
    void put(byte[] key, byte[] value);
    byte[] get(byte[] key);
    void commit(Entry entry);

    class Builder implements io.jiache.raft.common.Builder<StateMachine> {
        private Map<byte[], byte[]> state = new HashMap<>();

        public void setState(Map<byte[], byte[]> state) {
            this.state = state;
        }

        @Override
        public StateMachine build() {
            return new DefaultStateMachine(state);
        }
    }
}
