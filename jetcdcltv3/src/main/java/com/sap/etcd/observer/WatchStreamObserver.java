package com.sap.etcd.observer;

import etcdserverpb.Rpc.WatchResponse;
import io.grpc.stub.StreamObserver;

public abstract class WatchStreamObserver implements StreamObserver<WatchResponse> {

	protected long watchId;
	
	
	
	public long getWatchId() {
		return watchId;
	}

	@Override
	public void onCompleted() {
		System.out.println("complete");
		
	}

	@Override
	public void onError(Throwable t) {
t.printStackTrace();		
	}

}
