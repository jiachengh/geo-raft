package io.jiache.raft.server.secretary;

import io.grpc.stub.StreamObserver;
import io.jiache.raft.rpc.secretary.*;

public class SecretaryService extends SecretaryServiceGrpc.SecretaryServiceImplBase {
    @Override
    public void hire(SecretaryHireRequest request, StreamObserver<SecretaryHireResponse> responseObserver) {
        super.hire(request, responseObserver);
    }

    @Override
    public void status(SecretaryStatusRequest request, StreamObserver<SecretaryStatusResponse> responseObserver) {
        super.status(request, responseObserver);
    }

    @Override
    public void append(SecretaryAppendRequest request, StreamObserver<SecretaryAppendResponse> responseObserver) {
        super.append(request, responseObserver);
    }
}
