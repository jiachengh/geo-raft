package io.jiache.raft.server.raft.service;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import io.jiache.raft.rpc.client.*;
import io.jiache.raft.server.raft.RaftContext;
import io.jiache.raft.server.raft.State;
import io.jiache.raft.server.storage.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService extends ClientServiceGrpc.ClientServiceImplBase {
    private RaftContext context;

    private void leaderPut(PutRequest request) throws InterruptedException {
        // 放到log里
        long index = context.getLog().append(
                Entry.newBuilder()
                        .setKey(request.getKey().toByteArray())
                        .setValue(request.getValue().toByteArray())
                        .setTerm(context.getTerm())
                        .build()
        );
        // 等到commit大于index，则成功
        Long lastCommitIndex = context.getLastCommitIndex();
        while (lastCommitIndex < index) {
            synchronized (lastCommitIndex) {
                lastCommitIndex.wait();
            }
        }
    }

    private void followerPut(PutRequest request) {
       // TODO
    }

    @Override
    public void put(PutRequest request, StreamObserver<PutResponse> responseObserver) {
        State currentState = context.getState();
        try {
            if (currentState == State.LEADER) {
                leaderPut(request);
            }
            if (currentState == State.FOLLOWER) {
                followerPut(request);
            }
            if (currentState == State.CANDIDATE) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PutResponse response = PutResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private List<byte[]> leaderGet(GetRequest request) throws InterruptedException {
        // 没有确认是真的leader
        // TODO
        Long lastApplyIndex = context.getLastApplyIndex();
        while (lastApplyIndex < context.getLastCommitIndex()) {
            synchronized (lastApplyIndex) {
                lastApplyIndex.wait();
            }
        }
        List<byte[]> results = new ArrayList<>();
        request.getKeysList().stream()
                .map(ByteString::toByteArray)
                .forEach(key -> results.add(context.getStateMachine().get(key)));
        return results;
    }

    private List<byte[]> followerGet(GetRequest request) throws InterruptedException {
        // 没有向leader拿commitIndex TODO
        Long lastApplyIndex = context.getLastApplyIndex();
        while (lastApplyIndex < context.getLastCommitIndex()) {
            synchronized (lastApplyIndex) {
                lastApplyIndex.wait();
            }
        }
        List<byte[]> results = new ArrayList<>();
        request.getKeysList().stream()
                .map(ByteString::toByteArray)
                .forEach(key -> results.add(context.getStateMachine().get(key)));
        return results;
    }

    @Override
    public void get(GetRequest request, StreamObserver<GetResponse> responseObserver) {
        State currentState = context.getState();
        List<byte[]> result = null;
        try {
            if (currentState == State.LEADER) {
                result = leaderGet(request);
            }
            if (currentState == State.FOLLOWER) {
                result = followerGet(request);
            }
            if (currentState == State.CANDIDATE) {
                result = new ArrayList<>();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        responseObserver.onNext(
                GetResponse.newBuilder()
                        .addAllValues(result.stream().map(ByteString::copyFrom).collect(Collectors.toList()))
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void compaction(CompactionRequest request, StreamObserver<CompactionResponse> responseObserver) {
        super.compaction(request, responseObserver);
    }

    @Override
    public void snapshot(SnapshotRequest request, StreamObserver<SnapshotResponse> responseObserver) {
        super.snapshot(request, responseObserver);
    }

    @Override
    public void status(StatusRequest request, StreamObserver<StatusResponse> responseObserver) {
        super.status(request, responseObserver);
    }
}
