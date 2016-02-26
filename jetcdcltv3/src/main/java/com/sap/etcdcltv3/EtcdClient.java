package com.sap.etcdcltv3;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import com.google.protobuf.ByteString;
import etcdserverpb.KVGrpc;
import etcdserverpb.Rpc.PutRequest;
import etcdserverpb.Rpc.PutResponse;
import etcdserverpb.Rpc.RangeRequest;
import etcdserverpb.Rpc.RangeResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EtcdClient {
	private static final Logger logger = Logger.getLogger(EtcdClient.class.getName());
	private final ManagedChannel channel;
	private final KVGrpc.KVBlockingStub blockingStub;

	public EtcdClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
		blockingStub = KVGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public String range(ByteString key) {
		logger.info("try to get key:" + key.toStringUtf8());
		RangeRequest request = RangeRequest.newBuilder().setKey(key).build();
		RangeResponse response = blockingStub.range(request);
		String result = response.getKvs(0).getValue().toStringUtf8();
		return result;
	}

	public void put(ByteString key, ByteString value, ByteString lease) {
		logger.info("try to put key:" + key.toStringUtf8() + " value:" + value.toStringUtf8() + " lease:"
				+ lease.toStringUtf8());
		PutRequest request = PutRequest.newBuilder().setKey(key).setValue(value).build();
		PutResponse response = blockingStub.put(request);
	}

}
