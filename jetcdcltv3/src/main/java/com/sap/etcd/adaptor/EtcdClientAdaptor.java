package com.sap.etcd.adaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.sap.etcd.etcdcltv3.EtcdClient;
import com.sap.etcd.observer.WatchStreamObserver;

import etcdserverpb.Rpc.WatchResponse;
import io.grpc.stub.StreamObserver;

public class EtcdClientAdaptor {
	EtcdClient etcdClient;
	private static final Logger logger = Logger.getLogger(EtcdClient.class.getName());

	static Map<WatchListener, Long> watchIds = new ConcurrentHashMap<WatchListener, Long>();

	private int ttl = 10;
	
	public EtcdClientAdaptor(String host, int port) {
		etcdClient = new EtcdClient(host, port);
	}

	public void create(String path, boolean isDir) {
		// keep alive when adding a service url
		if (!isDir) {
			long leaseId = etcdClient.createLease(ttl);
			etcdClient.put(path, leaseId);
			etcdClient.keepAliveLease(leaseId);
		} else {
			etcdClient.put(path);
		}
	}

	public List<String> addWatchListener(final String path, final WatchListener watchListener) {
		final String range_end = getRangeEnd(path);
		List<String> keys = etcdClient.rangeKey(path, range_end);
		List<String> serviceUrls = processPathes(path,keys);
		StreamObserver<WatchResponse> responseObserver = new WatchStreamObserver() {
			@Override
			public void onNext(WatchResponse response) {
				if (response.getCreated()) {
					watchId = response.getWatchId();
					watchIds.put(watchListener, watchId);
				} else {
					// delete or create events
					List<String> keys = etcdClient.rangeKey(path, range_end);
					List<String> newServiceUrls = processPathes(path,keys);
					watchListener.update(path, newServiceUrls);
				}
			}
		};

		etcdClient.watchPrefix(path, responseObserver);
		return serviceUrls;
	}

	private String getRangeEnd(String path) {
		char last = path.charAt(path.length() - 1);
		char end = (char) (last + 1);
		String sub = path.substring(0, path.length() - 1);
		return sub + end;
	}

	// remove prefix
	private List<String> processPathes(String path, List<String> chilren) {
		List<String> list = new ArrayList<String>();
		if (chilren.size() == 0) {
			return null;
		}
		for (String child : chilren) {
			if (child.length() > path.length())
				list.add(child.substring(path.length() + 1));
		}
		return list;
	}

	public void removeWatchListener(WatchListener watchListener) {
		long watchId = watchIds.get(watchListener);

		StreamObserver<WatchResponse> responseObserver = new WatchStreamObserver() {
			@Override
			public void onNext(WatchResponse response) {
				logger.info("cancel watch :" + response.getWatchId());
			}
		};
		etcdClient.cancelWatch(watchId, responseObserver);
	}

	public boolean isAvailable() {
		return etcdClient.isAvailable();
	}

	public void delete(String urlPath) {
		etcdClient.delete(urlPath);
	}
}
