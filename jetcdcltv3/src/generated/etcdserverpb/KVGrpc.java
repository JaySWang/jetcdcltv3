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
public class KVGrpc {

  private KVGrpc() {}

  public static final String SERVICE_NAME = "etcdserverpb.KV";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.RangeRequest,
      etcdserverpb.Rpc.RangeResponse> METHOD_RANGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.KV", "Range"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.RangeRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.RangeResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.PutRequest,
      etcdserverpb.Rpc.PutResponse> METHOD_PUT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.KV", "Put"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.PutRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.PutResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.DeleteRangeRequest,
      etcdserverpb.Rpc.DeleteRangeResponse> METHOD_DELETE_RANGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.KV", "DeleteRange"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.DeleteRangeRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.DeleteRangeResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.TxnRequest,
      etcdserverpb.Rpc.TxnResponse> METHOD_TXN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.KV", "Txn"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.TxnRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.TxnResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.CompactionRequest,
      etcdserverpb.Rpc.CompactionResponse> METHOD_COMPACT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.KV", "Compact"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.CompactionRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.CompactionResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<etcdserverpb.Rpc.HashRequest,
      etcdserverpb.Rpc.HashResponse> METHOD_HASH =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "etcdserverpb.KV", "Hash"),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.HashRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(etcdserverpb.Rpc.HashResponse.getDefaultInstance()));

  public static KVStub newStub(io.grpc.Channel channel) {
    return new KVStub(channel);
  }

  public static KVBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new KVBlockingStub(channel);
  }

  public static KVFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new KVFutureStub(channel);
  }

  public static interface KV {

    public void range(etcdserverpb.Rpc.RangeRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.RangeResponse> responseObserver);

    public void put(etcdserverpb.Rpc.PutRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.PutResponse> responseObserver);

    public void deleteRange(etcdserverpb.Rpc.DeleteRangeRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.DeleteRangeResponse> responseObserver);

    public void txn(etcdserverpb.Rpc.TxnRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.TxnResponse> responseObserver);

    public void compact(etcdserverpb.Rpc.CompactionRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.CompactionResponse> responseObserver);

    public void hash(etcdserverpb.Rpc.HashRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.HashResponse> responseObserver);
  }

  public static interface KVBlockingClient {

    public etcdserverpb.Rpc.RangeResponse range(etcdserverpb.Rpc.RangeRequest request);

    public etcdserverpb.Rpc.PutResponse put(etcdserverpb.Rpc.PutRequest request);

    public etcdserverpb.Rpc.DeleteRangeResponse deleteRange(etcdserverpb.Rpc.DeleteRangeRequest request);

    public etcdserverpb.Rpc.TxnResponse txn(etcdserverpb.Rpc.TxnRequest request);

    public etcdserverpb.Rpc.CompactionResponse compact(etcdserverpb.Rpc.CompactionRequest request);

    public etcdserverpb.Rpc.HashResponse hash(etcdserverpb.Rpc.HashRequest request);
  }

  public static interface KVFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.RangeResponse> range(
        etcdserverpb.Rpc.RangeRequest request);

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.PutResponse> put(
        etcdserverpb.Rpc.PutRequest request);

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.DeleteRangeResponse> deleteRange(
        etcdserverpb.Rpc.DeleteRangeRequest request);

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.TxnResponse> txn(
        etcdserverpb.Rpc.TxnRequest request);

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.CompactionResponse> compact(
        etcdserverpb.Rpc.CompactionRequest request);

    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.HashResponse> hash(
        etcdserverpb.Rpc.HashRequest request);
  }

  public static class KVStub extends io.grpc.stub.AbstractStub<KVStub>
      implements KV {
    private KVStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVStub(channel, callOptions);
    }

    @java.lang.Override
    public void range(etcdserverpb.Rpc.RangeRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.RangeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RANGE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void put(etcdserverpb.Rpc.PutRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.PutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PUT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deleteRange(etcdserverpb.Rpc.DeleteRangeRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.DeleteRangeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_RANGE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void txn(etcdserverpb.Rpc.TxnRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.TxnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TXN, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void compact(etcdserverpb.Rpc.CompactionRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.CompactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_COMPACT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void hash(etcdserverpb.Rpc.HashRequest request,
        io.grpc.stub.StreamObserver<etcdserverpb.Rpc.HashResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_HASH, getCallOptions()), request, responseObserver);
    }
  }

  public static class KVBlockingStub extends io.grpc.stub.AbstractStub<KVBlockingStub>
      implements KVBlockingClient {
    private KVBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.RangeResponse range(etcdserverpb.Rpc.RangeRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RANGE, getCallOptions(), request);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.PutResponse put(etcdserverpb.Rpc.PutRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PUT, getCallOptions(), request);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.DeleteRangeResponse deleteRange(etcdserverpb.Rpc.DeleteRangeRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_RANGE, getCallOptions(), request);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.TxnResponse txn(etcdserverpb.Rpc.TxnRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TXN, getCallOptions(), request);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.CompactionResponse compact(etcdserverpb.Rpc.CompactionRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_COMPACT, getCallOptions(), request);
    }

    @java.lang.Override
    public etcdserverpb.Rpc.HashResponse hash(etcdserverpb.Rpc.HashRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_HASH, getCallOptions(), request);
    }
  }

  public static class KVFutureStub extends io.grpc.stub.AbstractStub<KVFutureStub>
      implements KVFutureClient {
    private KVFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KVFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KVFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KVFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.RangeResponse> range(
        etcdserverpb.Rpc.RangeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RANGE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.PutResponse> put(
        etcdserverpb.Rpc.PutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PUT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.DeleteRangeResponse> deleteRange(
        etcdserverpb.Rpc.DeleteRangeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_RANGE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.TxnResponse> txn(
        etcdserverpb.Rpc.TxnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TXN, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.CompactionResponse> compact(
        etcdserverpb.Rpc.CompactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_COMPACT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<etcdserverpb.Rpc.HashResponse> hash(
        etcdserverpb.Rpc.HashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_HASH, getCallOptions()), request);
    }
  }

  private static final int METHODID_RANGE = 0;
  private static final int METHODID_PUT = 1;
  private static final int METHODID_DELETE_RANGE = 2;
  private static final int METHODID_TXN = 3;
  private static final int METHODID_COMPACT = 4;
  private static final int METHODID_HASH = 5;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KV serviceImpl;
    private final int methodId;

    public MethodHandlers(KV serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RANGE:
          serviceImpl.range((etcdserverpb.Rpc.RangeRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.RangeResponse>) responseObserver);
          break;
        case METHODID_PUT:
          serviceImpl.put((etcdserverpb.Rpc.PutRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.PutResponse>) responseObserver);
          break;
        case METHODID_DELETE_RANGE:
          serviceImpl.deleteRange((etcdserverpb.Rpc.DeleteRangeRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.DeleteRangeResponse>) responseObserver);
          break;
        case METHODID_TXN:
          serviceImpl.txn((etcdserverpb.Rpc.TxnRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.TxnResponse>) responseObserver);
          break;
        case METHODID_COMPACT:
          serviceImpl.compact((etcdserverpb.Rpc.CompactionRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.CompactionResponse>) responseObserver);
          break;
        case METHODID_HASH:
          serviceImpl.hash((etcdserverpb.Rpc.HashRequest) request,
              (io.grpc.stub.StreamObserver<etcdserverpb.Rpc.HashResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final KV serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_RANGE,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.RangeRequest,
              etcdserverpb.Rpc.RangeResponse>(
                serviceImpl, METHODID_RANGE)))
        .addMethod(
          METHOD_PUT,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.PutRequest,
              etcdserverpb.Rpc.PutResponse>(
                serviceImpl, METHODID_PUT)))
        .addMethod(
          METHOD_DELETE_RANGE,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.DeleteRangeRequest,
              etcdserverpb.Rpc.DeleteRangeResponse>(
                serviceImpl, METHODID_DELETE_RANGE)))
        .addMethod(
          METHOD_TXN,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.TxnRequest,
              etcdserverpb.Rpc.TxnResponse>(
                serviceImpl, METHODID_TXN)))
        .addMethod(
          METHOD_COMPACT,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.CompactionRequest,
              etcdserverpb.Rpc.CompactionResponse>(
                serviceImpl, METHODID_COMPACT)))
        .addMethod(
          METHOD_HASH,
          asyncUnaryCall(
            new MethodHandlers<
              etcdserverpb.Rpc.HashRequest,
              etcdserverpb.Rpc.HashResponse>(
                serviceImpl, METHODID_HASH)))
        .build();
  }
}
