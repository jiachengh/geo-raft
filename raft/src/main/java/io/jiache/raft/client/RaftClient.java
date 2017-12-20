package io.jiache.raft.client;


import io.jiache.raft.common.Address;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface RaftClient {
    enum State {
        CONNECTED,
        CLOSED
    }

    static Builder newBuilder() {
        return new Builder();
    }

    State state();

    CompletableFuture<Byte[]> get(Byte[] key);

    CompletableFuture<Void> put(Byte[] key, Byte[] value);

    CompletableFuture<RaftClient> connect();

    CompletableFuture<Void> close();

    final class Builder implements io.jiache.raft.common.Builder<RaftClient> {
        private Map<Integer, Address> cluster;
        private Duration timeout;
        private AddressSelector addressSelector;

        private Builder() {
        }

        public Builder cluster(Map<Integer, Address> cluster) {
            this.cluster = cluster;
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder addressSelector(AddressSelector addressSelector) {
            this.addressSelector = addressSelector;
            return this;
        }

        @Override
        public RaftClient build() {
            return new DefaultRaftClient(
                    cluster,
                    timeout,
                    addressSelector
            );
        }
    }
}
