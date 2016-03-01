package com.sap.etcdcltv3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import com.google.protobuf.ByteString;

import etcdserverpb.KVGrpc;
import etcdserverpb.LeaseGrpc;
import etcdserverpb.Rpc.DeleteRangeRequest;
import etcdserverpb.Rpc.DeleteRangeResponse;
import etcdserverpb.Rpc.LeaseCreateRequest;
import etcdserverpb.Rpc.LeaseCreateResponse;
import etcdserverpb.Rpc.LeaseKeepAliveRequest;
import etcdserverpb.Rpc.LeaseKeepAliveResponse;
import etcdserverpb.Rpc.PutRequest;
import etcdserverpb.Rpc.PutResponse;
import etcdserverpb.Rpc.RangeRequest;
import etcdserverpb.Rpc.RangeResponse;
import etcdserverpb.Rpc.WatchCancelRequest;
import etcdserverpb.Rpc.WatchCreateRequest;
import etcdserverpb.Rpc.WatchRequest;
import etcdserverpb.Rpc.WatchResponse;
import etcdserverpb.WatchGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import storagepb.Kv.KeyValue;

public class EtcdClient {
	private static final Logger logger = Logger.getLogger(EtcdClient.class.getName());
	private final ManagedChannel channel;
	private final KVGrpc.KVBlockingStub blockingStub;
	private final KVGrpc.KVStub kvStub;
	private final WatchGrpc.WatchStub watchStub;
	private final LeaseGrpc.LeaseStub leaseStub;
	private final LeaseGrpc.LeaseBlockingStub leaseBlockingStub;
	
	public EtcdClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
		blockingStub = KVGrpc.newBlockingStub(channel);
		kvStub = KVGrpc.newStub(channel);
		watchStub = WatchGrpc.newStub(channel);
		leaseStub = LeaseGrpc.newStub(channel);
		leaseBlockingStub = LeaseGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public String range(ByteString key) {
		logger.info("try to get key:" + key.toStringUtf8());
		RangeRequest request = RangeRequest.newBuilder().setKey(key).build();
		RangeResponse response = blockingStub.range(request);
		String result = null; 
		if(response.getKvsCount()>0){
		result = response.getKvs(0).getValue().toStringUtf8();
		}
		return result;
	}

	public List<String> range(ByteString key, ByteString range_end) {
		logger.info("try to range key:" + key + "to " +range_end);
		RangeRequest request = RangeRequest.newBuilder().setKey(key).setRangeEnd(range_end).build();
		RangeResponse response = blockingStub.range(request);
		List<KeyValue> kvResults = response.getKvsList();
		List<String> results = new ArrayList<String>();
		for(KeyValue kv:kvResults){
			results.add(kv.getValue().toStringUtf8());
		}
		return results;
	}
	
	public void put(ByteString key, ByteString value, long lease) {
		logger.info("try to put key:" + key.toStringUtf8() + " value:" + value.toStringUtf8() + " lease:"
				+ lease);
		PutRequest request = PutRequest.newBuilder().setKey(key).setValue(value).setLease(lease).build();
		PutResponse response = blockingStub.put(request);
	}

	public void put(ByteString key, ByteString value) {
		logger.info("try to put key:" + key.toStringUtf8() + " value:" + value.toStringUtf8() );
		PutRequest request = PutRequest.newBuilder().setKey(key).setValue(value).build();
		PutResponse response = blockingStub.put(request);
	}
	
	public void watchPrefix(ByteString prefix,StreamObserver<WatchResponse> responseObserver){
		logger.info("try to watch prefix:" + prefix.toStringUtf8() );

		StreamObserver<WatchRequest> requestObserver =watchStub.watch(responseObserver);
		WatchCreateRequest createRequest = WatchCreateRequest.newBuilder().setPrefix(prefix).build();
	    WatchRequest request = WatchRequest.newBuilder().setCreateRequest(createRequest).build();
		requestObserver.onNext(request);
	}
	
	public void watch(ByteString key,StreamObserver<WatchResponse> responseObserver){
		logger.info("try to watch key:" + key.toStringUtf8() );
		StreamObserver<WatchRequest> requestObserver =watchStub.watch(responseObserver);
		WatchCreateRequest createRequest = WatchCreateRequest.newBuilder().setKey(key).build();
	    WatchRequest request = WatchRequest.newBuilder().setCreateRequest(createRequest).build();
		requestObserver.onNext(request);
	}

	public long createLease(int ttl) {
		logger.info("try to create lease with ttl:" + ttl);
		LeaseCreateRequest request = LeaseCreateRequest.newBuilder().setTTL(ttl).build();	
		LeaseCreateResponse response = leaseBlockingStub.leaseCreate(request);	
		return response.getID();
	}
	
	public void keepAliveLease(final long id){
		final StreamObserver<LeaseKeepAliveResponse> responseObserver =new StreamObserver<LeaseKeepAliveResponse>(){
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable arg0) {
			}

			@Override
			public void onNext(LeaseKeepAliveResponse response) {
				logger.info("keep lease: "+response.getID()+" alive with ttl:" + response.getTTL());
				try {
					Thread.sleep(1000*response.getTTL()/2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				StreamObserver<LeaseKeepAliveRequest> requestObserver = leaseStub.leaseKeepAlive(this);
				LeaseKeepAliveRequest request = LeaseKeepAliveRequest.newBuilder().setID(id).build();
				requestObserver.onNext(request);
			}
			
		};
		
		Thread keepalive = new Thread(new Runnable(){
			@Override
			public void run() {
				StreamObserver<LeaseKeepAliveRequest> requestObserver = leaseStub.leaseKeepAlive(responseObserver);
				LeaseKeepAliveRequest request = LeaseKeepAliveRequest.newBuilder().setID(id).build();
				requestObserver.onNext(request);				
			}
			
		});
		keepalive.start();

	}

	public void cancelWatch(long watchId,StreamObserver<WatchResponse> responseObserver) {
		logger.info("try to cancel watch :" + watchId );
		StreamObserver<WatchRequest> requestObserver =watchStub.watch(responseObserver);
		WatchCancelRequest cancelRequest = WatchCancelRequest.newBuilder().setWatchId(watchId).build();
	    WatchRequest request = WatchRequest.newBuilder().setCancelRequest(cancelRequest).build();
		requestObserver.onNext(request);
	}

	public long delete(ByteString key) {
		DeleteRangeRequest request = DeleteRangeRequest.newBuilder().setKey(key).build();
		DeleteRangeResponse response = blockingStub.deleteRange(request);
		long numberOfDeleted = response.getDeleted();
		return numberOfDeleted;
	}
	
	public long delete(ByteString key,ByteString range_end) {
		DeleteRangeRequest request = DeleteRangeRequest.newBuilder().setKey(key).setRangeEnd(range_end).build();
		DeleteRangeResponse response = blockingStub.deleteRange(request);
		long numberOfDeleted = response.getDeleted();
		return numberOfDeleted;
	}
}
