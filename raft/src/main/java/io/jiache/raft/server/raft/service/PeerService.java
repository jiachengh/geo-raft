package io.jiache.raft.server.raft.service;

import io.grpc.stub.StreamObserver;
import io.jiache.raft.rpc.peer.*;
import io.jiache.raft.server.raft.RaftContext;
import io.jiache.raft.server.raft.State;
import io.jiache.raft.server.storage.Entry;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PeerService extends PeerServiceGrpc.PeerServiceImplBase {
    private RaftContext context;
    @Override
    public void vote(VoteRequest request, StreamObserver<VoteResponse> responseObserver) {
        super.vote(request, responseObserver);
    }

    @Override
    public void append(AppendRequest request, StreamObserver<AppendResponse> responseObserver) {
        if (context.getState() == State.FOLLOWER && request.getTerm() >= context.getTerm()) {
            List<Entry> entries = request.getEntriesList()
                    .stream()
                    .map(entry -> Entry.newBuilder()
                            .setKey(entry.getKey().toByteArray())
                            .setTerm(request.getTerm())
                            .setValue(entry.getValue().toByteArray())
                            .build())
                    .collect(Collectors.toList());
            // 更新heatbeat时间
            context.setLastReceiveHeartBeatTime(Duration.ofMillis(System.currentTimeMillis()));

            // log match
            if (entries.size() != 0 && request.getPrevLogIndex() == context.getLog().getLastIndex()) {
                entries.forEach(context.getLog()::append);
            }
        }
        super.append(request, responseObserver);
    }
}
