package io.jiache.raft.server.storage;

import io.jiache.raft.common.Builder;

import java.util.ArrayList;
import java.util.List;

public interface Log {
    Long getLastIndex();
    Long append(Entry entry);
    List<Entry> rangeEntry(long beginIndex, long lastIndex);

    default Builder newBuilder() {
        return new Builder();
    }

    class Builder implements io.jiache.raft.common.Builder<Log> {
        private Long beginIndex = 0L;
        private Long lastIndex = -1L;
        private List<Entry> journal = new ArrayList<>();

        public void setBeginIndex(Long beginIndex) {
            this.beginIndex = beginIndex;
        }

        public void setLastIndex(Long lastIndex) {
            this.lastIndex = lastIndex;
        }

        public void setJournal(List<Entry> journal) {
            this.journal = journal;
        }

        @Override
        public Log build() {
            return new DefaultLog(beginIndex, lastIndex, journal);
        }
    }
}
