package policies;

import java.util.ArrayList;

import sysc4005.SystemData;

public final class RandomizedPolicy extends AbstractPolicy {
	SystemData data;
	
	
	public RandomizedPolicy(SystemData data) {
		this.data = data;
	}

	@Override
	public void allocateServer(int t) {
		ArrayList<Integer> connectedQueues = new ArrayList<Integer>();
		for (int i = 0; i < data.getN(); i++) {
			if (data.isConnected(i, t)) {
				connectedQueues.add(i);
			}
		}		
		
		int count = connectedQueues.size();
		if (count < 1) {
			data.setServerState(t, SystemData.SERVER_IDLE);
		}
		int n = (int) Math.floor(Math.random() * count + 1);
		
		data.setServerState(t, n);		
	}	
}
