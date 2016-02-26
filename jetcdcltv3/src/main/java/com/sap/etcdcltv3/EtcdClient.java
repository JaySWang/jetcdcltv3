package com.sap.etcdcltv3;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;

import etcdserverpb.KVGrpc;
import etcdserverpb.Rpc.PutRequest;
import etcdserverpb.Rpc.PutResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EtcdClient {
	private static final Logger logger = Logger.getLogger(EtcdClient.class.getName());

	  private final ManagedChannel channel;
	  private final KVGrpc.KVBlockingStub blockingStub;
	  
	  
	  public EtcdClient(String host, int port){
		  channel = ManagedChannelBuilder.forAddress(host, port)
			        .usePlaintext(true)
			        .build();
			    blockingStub = KVGrpc.newBlockingStub(channel);
	  }
	
	  public void shutdown() throws InterruptedException {
		    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		  }
	  
	  public String get(String key){
		  logger.info("try to get key:"+key);
		  String result=null;
	
		 		  return result;
		  
	  }
	  
	  public String put(ByteString key,ByteString value,ByteString lease){
		  logger.info("try to put key:"+key+" value:"+value+" lease:"+lease);
		  String result=null;
		  
		  PutRequest request = PutRequest.newBuilder().setKey(key).setValue(value).build();
		  PutResponse  response = blockingStub.put(request);
		  System.out.println(response);
		 		  return response.toString();
	  }
	  
	  public static void main(String[] args) throws Exception {
		  EtcdClient client = new EtcdClient("localhost", 2378);
		    try {
		      /* Access a service running on the local machine on port 50051 */
		      String user = "world";
		      if (args.length > 0) {
		        user = args[0]; /* Use the arg as the name to greet if provided */
		      }
		      ByteString key = ByteString.copyFromUtf8("etcd");
		      ByteString value = ByteString.copyFromUtf8("good");

		      client.put(key,value,null);
		    } finally {
		      client.shutdown();
		    }
		  }
	  

}
