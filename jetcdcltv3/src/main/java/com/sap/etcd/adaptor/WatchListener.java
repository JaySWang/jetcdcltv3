package com.sap.etcd.adaptor;

import java.util.List;

public interface WatchListener {
	
	public void update(String path, List<String> newChildren);

}
