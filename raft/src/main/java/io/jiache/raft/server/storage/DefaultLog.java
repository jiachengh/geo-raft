package io.jiache.raft.server.storage;

import java.util.ArrayList;
import java.util.List;

public class DefaultLog implements Log {
    private volatile Long beginIndex;
    private volatile Long lastIndex;
    private volatile List<Entry> journal;
    private final Object lock = new Object();

    DefaultLog(Long beginIndex, Long lastIndex, List<Entry> journal) {
        this.beginIndex = beginIndex;
        this.lastIndex = lastIndex;
        this.journal = journal;
    }

    @Override
    public Long getLastIndex() {
        return lastIndex;
    }

    private Long getBeginIndex() {
        return beginIndex;
    }

    @Override
    public Long append(Entry entry) {
        synchronized (lock) {
            journal.add(entry);
            return ++lastIndex;
        }
    }

    @Override
    public List<Entry> rangeEntry(long beginIndex, long lastIndex) {
        if (getLastIndex() >= lastIndex && getBeginIndex() <= beginIndex) {
            return journal.subList(
                    (int)(beginIndex - getBeginIndex()),
                    (int)(lastIndex - getBeginIndex() + 1)
            );
        }
        return new ArrayList<>();
    }
}
