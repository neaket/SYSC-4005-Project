package policies;

import java.util.ArrayList;

import sysc4005.SystemData;

public final class LongestConnectedQueuePolicy extends AbstractPolicy {

	public LongestConnectedQueuePolicy(SystemData data) {
		super(data);
	}

	@Override
	public void allocateServer(int t) {
		ArrayList<Integer> longestQueues = new ArrayList<Integer>();
		
		int longestLength = 0; 
		for (int i = 0; i < data.getN(); i++ ) {
			if (data.isConnected(i, t) && !data.isEmpty(i, t)) {
				int length = data.getQueueLength(i, t);
				if (length > longestLength) {
					longestQueues.clear();
					longestQueues.add(i);
				} else if (length == longestLength) {
					longestQueues.add(i);
				}
			}
		}
		
		if (longestLength == 0) {
			data.setServerState(t, SystemData.SERVER_IDLE);
		} else {
			if (longestQueues.size() == 1) {
				data.setServerState(t, longestQueues.get(0));
			} else {
				int n = (int) Math.floor(Math.random() * longestQueues.size() + 1);
				
				data.setServerState(t, longestQueues.get(n));	
			}
		}
		
		
		
	}

}
