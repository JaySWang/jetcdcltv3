package com.sap.etcdcltv3;

import java.util.List;

import org.junit.*;

import com.google.protobuf.ByteString;
import com.sap.etcd.observer.WatchStreamObserver;

import etcdserverpb.Rpc.WatchResponse;
import io.grpc.stub.StreamObserver;

public class EtcdClientTest {
	EtcdClient client;

	@Before
	public void init() {
		String host = "localhost";
		int port = 2378;
		client = new EtcdClient(host, port);
	}
 
	@Test
	public void testPut() {
		ByteString key = ByteString.copyFromUtf8("etcd");
		ByteString value = ByteString.copyFromUtf8("nice");
		client.put(key, value);
		Assert.assertTrue(true);
	}

	@Test
	public void testRange() {
		ByteString key1 = ByteString.copyFromUtf8("range1");
		ByteString value1 = ByteString.copyFromUtf8("good1");
		client.put(key1, value1);
		Assert.assertTrue(true);

		String result = client.range(key1);
		Assert.assertEquals(value1.toStringUtf8(), result);
		
		ByteString key2 = ByteString.copyFromUtf8("range2");
		ByteString value2 = ByteString.copyFromUtf8("good2");
		ByteString key3 = ByteString.copyFromUtf8("range3");
		ByteString value3 = ByteString.copyFromUtf8("good3");
		
		client.put(key2, value2);
		client.put(key3, value3);

		List<String> results = client.range(key1,key3);
		Assert.assertTrue(results.size()==2);
		Assert.assertTrue(results.contains(value1.toStringUtf8()));
		Assert.assertTrue(results.contains(value2.toStringUtf8()));
		Assert.assertFalse(results.contains(value3.toStringUtf8()));
	}

	@Test
	public void testWatch(){
		final ByteString key1 = ByteString.copyFromUtf8("range1");
		ByteString value1 = ByteString.copyFromUtf8("good1");
		final ByteString value2 = ByteString.copyFromUtf8("good2");

		client.put(key1, value1);
		Assert.assertTrue(true);
		StreamObserver<WatchResponse> responseObserver=new WatchStreamObserver(){
			@Override
			public void onNext(WatchResponse response) {
				if(response.getCreated()){
					watchId=response.getWatchId();
				}else{
					String key = response.getEventsList().get(0).getKv().getKey().toStringUtf8();
					String value = response.getEventsList().get(0).getKv().getValue().toStringUtf8();
				    Assert.assertTrue(value.equals(value2.toStringUtf8()));
				    Assert.assertTrue(key.equals(key1.toStringUtf8()));
				}
			}
			};
			
		client.watch(key1,responseObserver);
		client.put(key1, value2);
		Assert.assertTrue(true);

	}
	
	
	@Test
	public void testWatchPrefix(){
		final ByteString prefix = ByteString.copyFromUtf8("prefix");
		final ByteString key1 = ByteString.copyFromUtf8("prefixRange1");
		ByteString value1 = ByteString.copyFromUtf8("good1");
		final ByteString value2 = ByteString.copyFromUtf8("good2");

		client.put(key1, value1);
		Assert.assertTrue(true);
		StreamObserver<WatchResponse> responseObserver=new WatchStreamObserver(){
			@Override
			public void onNext(WatchResponse response) {
				if(response.getCreated()){
					watchId=response.getWatchId();
				}else{
					String key = response.getEventsList().get(0).getKv().getKey().toStringUtf8();
					String value = response.getEventsList().get(0).getKv().getValue().toStringUtf8();
				    Assert.assertTrue(value.equals(value2.toStringUtf8()));
				    Assert.assertTrue(key.equals(key1.toStringUtf8()));
				}
			}
		};
			
		client.watchPrefix(prefix,responseObserver);
		client.put(key1, value2);
		Assert.assertTrue(true);

	}
	
	@Test
	public void testLease(){
		int ttl =5;
		long leaseId = client.createLease(ttl);
		Assert.assertTrue(leaseId!=0);
		ByteString key = ByteString.copyFromUtf8("lease");
		ByteString value = ByteString.copyFromUtf8("good1");
		
		client.put(key, value, leaseId);
		String result = client.range(key);
		Assert.assertEquals(value.toStringUtf8(), result);
		try {
			Thread.sleep((ttl+1)*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expiredResult = client.range(key);
		Assert.assertNull(expiredResult);
		
		 leaseId = client.createLease(ttl);
		 
		 ByteString key2 = ByteString.copyFromUtf8("lease2");
		 ByteString value2 = ByteString.copyFromUtf8("good2");
			
		 client.put(key2, value2, leaseId);
		 client.keepAliveLease(leaseId);
		 try {
				Thread.sleep((ttl+1)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String aliveResult = client.range(key2);
			Assert.assertEquals(value2.toStringUtf8(), aliveResult);
	}
	
	
	@After
	public void end() {
		
		try {
			client.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
