package sysc4005.policies;

import java.util.ArrayList;

import sysc4005.SystemData;

public final class RandomizedPolicy extends AbstractPolicy {
	protected SystemData data;
	
	
	public RandomizedPolicy(SystemData data) {
		super(data);
	}

	@Override
	public void allocateServer(int t) {
		ArrayList<Integer> connectedQueues = new ArrayList<Integer>();
		for (int n = 0; n < data.getN(); n++) {
			if (data.isConnected(n, t)) {
				connectedQueues.add(n);
			}
		}		
		
		int count = connectedQueues.size();
		if (count < 1) {
			data.setServerState(t, SystemData.SERVER_IDLE);
		}
		int n = (int) Math.floor(Math.random() * count + 1);
		
		data.setServerState(t, connectedQueues.get(n));		
	}	
}
