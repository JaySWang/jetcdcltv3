package com.sap.etcd.adaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sap.etcd.observer.WatchStreamObserver;
import com.sap.etcdcltv3.EtcdClient;

import etcdserverpb.Rpc.WatchResponse;
import io.grpc.stub.StreamObserver;

public class EtcdClientAdaptor{
	EtcdClient etcdClient;
	static Map<WatchListener,Long> watchIds = new ConcurrentHashMap<WatchListener,Long>();
	
	public EtcdClientAdaptor(String host, int port) {
		etcdClient = new EtcdClient(host, port);
	}

	public void create(String path, boolean isDir) {
		//keep alive when adding a service url
		if(!isDir){
			long leaseId = etcdClient.createLease(10);
			etcdClient.put(path,leaseId);
			etcdClient.keepAliveLease(leaseId);
		}else{
		etcdClient.put(path);
		}

	}
	
	//remove prefix
	private List<String> processPathes(String path, List<String> chilren) {
		List<String> list =new ArrayList<String>();
		if(chilren.size()==0){
			return null;
		}
		for(String child :chilren){
			if(child.length()>path.length())
			list.add(child.substring(path.length()+1));
		}	
		return list;
	
	
	}

	public String getRangeEnd(String path) {
		char last = path.charAt(path.length()-1);
		char end = (char)(last+1);
		String sub = path.substring(0, path.length()-1);
		return sub+end;
	}

	public List<String> addChildListener(final String path, final WatchListener watchListener) {

		final String range_end = getRangeEnd(path);	

		List<String> children = processPathes(path,etcdClient.rangeKey(path, range_end));
		StreamObserver<WatchResponse>  responseObserver=new WatchStreamObserver(){
			@Override
			public void onNext(WatchResponse response) {
				if(response.getCreated()){
					watchId=response.getWatchId();
			//		watchIds.put(watchListener, watchId);
				}else{
					//delete or create events
					List<String> newChildren = processPathes(path,etcdClient.rangeKey(path, range_end));
					watchListener.update(path, newChildren);
				}
			}
		};
		
		etcdClient.watchPrefix(path, responseObserver);
		return children;
	
	}
}
