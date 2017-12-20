package io.jiache.raft.server.storage;


import java.io.Serializable;

public class Entry implements Serializable{
    private final byte[] key;
    private final byte[] value;
    private final Long term;

    private Entry(byte[] key, byte[] value, Long term) {
        this.key = key;
        this.value = value;
        this.term = term;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public byte[] getKey() {
        return key;
    }

    public byte[] getValue() {
        return value;
    }

    public Long getTerm() {
        return term;
    }

    public static class Builder implements io.jiache.raft.common.Builder<Entry> {
        private byte[] key;
        private byte[] value;
        private Long term;

        public Builder setKey(byte[] key) {
            this.key = key;
            return this;
        }

        public Builder setValue(byte[] value) {
            this.value = value;
            return this;
        }

        public Builder setTerm(Long term) {
            this.term = term;
            return this;
        }

        @Override
        public Entry build() {
            return new Entry(key, value, term);
        }
    }
}
