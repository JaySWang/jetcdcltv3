package etcdserverpb;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class LeaseGrpc {

  private LeaseGrpc() {}

  public static final String SERVICE_NAME = "etcdserverpb.Lease";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.LeaseCreateRequest,
      etcdserverpb.Rpc.LeaseCreateResponse> METHOD_LEASE_CREATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.Lease", "LeaseCreate"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.LeaseCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.LeaseCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.LeaseRevokeRequest,
      etcdserverpb.Rpc.LeaseRevokeResponse> METHOD_LEASE_REVOKE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.Lease", "LeaseRevoke"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.LeaseRevokeRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.LeaseRevokeResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.LeaseKeepAliveRequest,
      etcdserverpb.Rpc.LeaseKeepAliveResponse> METHOD_LEASE_KEEP_ALIVE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "etcdserverpb.Lease", "LeaseKeepAlive"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.LeaseKeepAliveRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.LeaseKeepAliveResponse.getDefaultInstance()));

  public static LeaseStub newStub(io.grpc.Channel channel) {
    return new LeaseStub(channel);
  }

  public static LeaseBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LeaseBlockingStub(channel);
  }

  public static LeaseFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LeaseFutureStub(channel);
  }

  public static interface Lease {

    public void leaseCreate(etcdserverpb.Rpc.LeaseCreateRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseCreateResponse> responseObserver);

    public void leaseRevoke(etcdserverpb.Rpc.LeaseRevokeRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseRevokeResponse> responseObserver);

    public io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseKeepAliveRequest> leaseKeepAlive(
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseKeepAliveResponse> responseObserver);
  }

  public static interface LeaseBlockingClient {

    public etcdserverpb.Rpc.LeaseCreateResponse leaseCreate(etcdserverpb.Rpc.LeaseCreateRequest request);

    public etcdserverpb.Rpc.LeaseRevokeResponse leaseRevoke(etcdserverpb.Rpc.LeaseRevokeRequest request);
  }

  public static interface LeaseFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.LeaseCreateResponse> leaseCreate(
        etcdserverpb.Rpc.LeaseCreateRequest request);

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.LeaseRevokeResponse> leaseRevoke(
        etcdserverpb.Rpc.LeaseRevokeRequest request);
  }

  public static class LeaseStub extends io.grpc.stub.AbstractStub<LeaseStub>
      implements Lease {
    private LeaseStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LeaseStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LeaseStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LeaseStub(channel, callOptions);
    }

    @java.lang.Override
    public void leaseCreate(etcdserverpb.Rpc.LeaseCreateRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LEASE_CREATE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void leaseRevoke(etcdserverpb.Rpc.LeaseRevokeRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseRevokeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LEASE_REVOKE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseKeepAliveRequest> leaseKeepAlive(
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseKeepAliveResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_LEASE_KEEP_ALIVE, getCallOptions()), responseObserver);
    }
  }

  public static class LeaseBlockingStub extends io.grpc.stub.AbstractStub<LeaseBlockingStub>
      implements LeaseBlockingClient {
    private LeaseBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LeaseBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LeaseBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LeaseBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.LeaseCreateResponse leaseCreate(etcdserverpb.Rpc.LeaseCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LEASE_CREATE, getCallOptions(), request);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.LeaseRevokeResponse leaseRevoke(etcdserverpb.Rpc.LeaseRevokeRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LEASE_REVOKE, getCallOptions(), request);
    }
  }

  public static class LeaseFutureStub extends io.grpc.stub.AbstractStub<LeaseFutureStub>
      implements LeaseFutureClient {
    private LeaseFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LeaseFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LeaseFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LeaseFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.LeaseCreateResponse> leaseCreate(
        etcdserverpb.Rpc.LeaseCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LEASE_CREATE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.LeaseRevokeResponse> leaseRevoke(
        etcdserverpb.Rpc.LeaseRevokeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LEASE_REVOKE, getCallOptions()), request);
    }
  }

  private static final int METHODID_LEASE_CREATE = 0;
  private static final int METHODID_LEASE_REVOKE = 1;
  private static final int METHODID_LEASE_KEEP_ALIVE = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final Lease serviceImpl;
    private final int methodId;

    public MethodHandlers(Lease serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LEASE_CREATE:
          serviceImpl.leaseCreate((etcdserverpb.Rpc.LeaseCreateRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseCreateResponse>) responseObserver);
          break;
        case METHODID_LEASE_REVOKE:
          serviceImpl.leaseRevoke((etcdserverpb.Rpc.LeaseRevokeRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseRevokeResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LEASE_KEEP_ALIVE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.leaseKeepAlive(
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.LeaseKeepAliveResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final Lease serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_LEASE_CREATE,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.LeaseCreateRequest,
              etcdserverpb.Rpc.LeaseCreateResponse>(
                serviceImpl, METHODID_LEASE_CREATE)))
        .addMethod(
          METHOD_LEASE_REVOKE,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.LeaseRevokeRequest,
              etcdserverpb.Rpc.LeaseRevokeResponse>(
                serviceImpl, METHODID_LEASE_REVOKE)))
        .addMethod(
          METHOD_LEASE_KEEP_ALIVE,
          asyncBidiStreamingCall(
            new MethodHandlers<
              etcdserverpb.Rpc.LeaseKeepAliveRequest,
              etcdserverpb.Rpc.LeaseKeepAliveResponse>(
                serviceImpl, METHODID_LEASE_KEEP_ALIVE)))
        .build();
  }
}
