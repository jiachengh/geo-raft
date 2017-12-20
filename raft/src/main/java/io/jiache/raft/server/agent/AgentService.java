package io.jiache.raft.server.agent;

import io.grpc.stub.StreamObserver;
import io.jiache.raft.rpc.agent.AgentServiceGrpc;
import io.jiache.raft.rpc.agent.FindSecretaryRequest;
import io.jiache.raft.rpc.agent.FindSecretaryResponse;

public class AgentService extends AgentServiceGrpc.AgentServiceImplBase {

    @Override
    public void findSecretary(FindSecretaryRequest request, StreamObserver<FindSecretaryResponse> responseObserver) {
        super.findSecretary(request, responseObserver);
    }
}
