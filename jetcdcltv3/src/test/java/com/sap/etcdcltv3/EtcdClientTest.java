package com.sap.etcdcltv3;

import org.junit.*;

import com.google.protobuf.ByteString;

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
		ByteString lease = null;
		client.put(key, value, lease);
		Assert.assertTrue(true);

	}

	@Test
	public void testRange() {
		ByteString key = ByteString.copyFromUtf8("range");
		ByteString value = ByteString.copyFromUtf8("good");
		ByteString lease = null;
		client.put(key, value, lease);
		Assert.assertTrue(true);

		String result = client.range(key);
		Assert.assertEquals(value.toStringUtf8(), result);
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
