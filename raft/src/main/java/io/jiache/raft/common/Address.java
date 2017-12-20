package io.jiache.raft.common;

public class Address {
    private final String host;
    private final Integer port;

    private Address(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder implements io.jiache.raft.common.Builder<Address> {
        private String host;
        private Integer port;

        private Builder() {
        }

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setPort(Integer port) {
            this.port = port;
            return this;
        }

        @Override
        public Address build() {
            return new Address(host, port);
        }
    }

}
