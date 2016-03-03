package com.sap.etcdcltv3;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.google.protobuf.ByteString;
import com.sap.etcd.adaptor.EtcdClientAdaptor;
import com.sap.etcd.observer.WatchStreamObserver;

import etcdserverpb.Rpc.WatchResponse;
import io.grpc.stub.StreamObserver;

public class EtcdClientTest {
	static EtcdClient client;
	
	@BeforeClass
	public static void init() {
		String host = "127.0.0.1";
		int port = 2378;
		client = new EtcdClient(host, port);
	}
 
	@Test
	public void testPut() {
		String key = "etcd";
		String value = "nice";
		client.put(key, value);
		Assert.assertTrue(true);
	//	Assert.assertEquals(1,client.delete(key));
		client.delete(key);
	}

	@Test
	public void testRange() {
		String key1 = "range1";
		String value1 = "good1";
		client.put(key1, value1);
		Assert.assertTrue(true);

		String result = client.range(key1);
		Assert.assertEquals(value1, result);
		
		String key2 = "range2";
		String value2 = "good2";
		String key3 = "range3";
		String value3 = "good3";
		
		client.put(key2, value2);
		client.put(key3, value3);

		List<String> results = client.range(key1,key3);
		Assert.assertTrue(results.size()==2);
		Assert.assertTrue(results.contains(value1));
		Assert.assertTrue(results.contains(value2));
		Assert.assertFalse(results.contains(value3));
		
		String key4 = "range4";
		//Assert.assertEquals(3,client.delete(key1,key4));
		client.delete(key1,key4);
	}

	@Test
	public void testWatch(){
		final String key1 = "watch1";
		String value1 = "good1";
		final String value2 = "good2";
		final String value3 = "good3";
		final List<String> results = new ArrayList<String>();
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
					results.add(value);
				    Assert.assertTrue(key.equals(key1));
				}
			}
			};
			
		client.watch(key1,responseObserver);
		client.put(key1, value2);
		client.put(key1, value1);
		
		client.cancelWatch(((WatchStreamObserver) responseObserver).getWatchId(),responseObserver);
		
		client.put(key1, value3);
		Assert.assertEquals(2,results.size());
		//Assert.assertEquals(1,client.delete(key1));
		client.delete(key1);
	}
	
	
	@Test
	public void testWatchPrefix(){
		final String prefix = "prefix";
		final String key1 = "prefixRange1";
		String value1 = "good1";
		final String value2 = "good2";
		final String value3 = "good3";
		client.put(key1, value1);
		Assert.assertTrue(true);
		
		final List<String> results = new ArrayList<String>();
		StreamObserver<WatchResponse> responseObserver=new WatchStreamObserver(){
			@Override
			public void onNext(WatchResponse response) {
				if(response.getCreated()){
					watchId=response.getWatchId();
				}else{
					String key = response.getEventsList().get(0).getKv().getKey().toStringUtf8();
					String value = response.getEventsList().get(0).getKv().getValue().toStringUtf8();
					results.add(value);
				    Assert.assertTrue(key.equals(key1));
				}
			}
		};
			
		client.watchPrefix(prefix,responseObserver);
		client.put(key1, value2);
		client.put(key1, value1);
		
		client.cancelWatch(((WatchStreamObserver) responseObserver).getWatchId(),responseObserver);
		
		client.put(key1, value3);
		Assert.assertEquals(2,results.size());
		
	//	Assert.assertEquals(1,client.delete(key1));
		client.delete(key1);
	}
	
	@Test
	public void testLease(){
		int ttl =5;
		long leaseId = client.createLease(ttl);
		Assert.assertTrue(leaseId!=0);
		String key1 = "lease1";
		String value = "good1";
		
		client.put(key1, value, leaseId);
		String result = client.range(key1);
		Assert.assertEquals(value, result);
		try {
			Thread.sleep((ttl+1)*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expiredResult = client.range(key1);
		Assert.assertNull(expiredResult);
		
		 leaseId = client.createLease(ttl);
		 
		 String key2 = "lease2";
		 String value2 = "good2";
			
		 client.put(key2, value2, leaseId);
		 client.keepAliveLease(leaseId);
		 try {
				Thread.sleep((ttl+1)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String aliveResult = client.range(key2);
			Assert.assertEquals(value2, aliveResult);
			
			String key3 = "lease3";
		//	Assert.assertEquals(1,client.delete(key1,key3));
			client.delete(key1,key3);
	}
	
	//@AfterClass
	public static void end() {
		try {
			client.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
