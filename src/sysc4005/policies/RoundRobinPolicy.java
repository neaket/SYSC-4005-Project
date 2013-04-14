package sysc4005.policies;

import sysc4005.SystemData;

public final class RoundRobinPolicy extends AbstractPolicy {

	public RoundRobinPolicy(SystemData data) {
		super(data);
	}

	int currentQueue = 0;
	
	@Override
	public void allocateServer(int t) {
		
		if (!data.isConnected(currentQueue, t) || data.isEmpty(currentQueue, t)) {
			data.setServerState(t, SystemData.SERVER_IDLE);
		}
		data.setServerState(t, currentQueue);
		
		currentQueue++;
		if (currentQueue == data.getN()) {
			currentQueue = 0;
		}		
	}

}
