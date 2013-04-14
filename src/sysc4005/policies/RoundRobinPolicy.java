package sysc4005.policies;

import sysc4005.SimulationSystem;

public final class RoundRobinPolicy extends AbstractPolicy {
	int currentQueue = 0;
	
	@Override
	public void allocateServer(int t) {
		
		if (!system.isConnected(currentQueue, t) || system.isEmpty(currentQueue, t - 1)) {
			system.setServerState(t, SimulationSystem.SERVER_IDLE);
			
		} else {
			system.setServerState(t, currentQueue);
		}
		
		
		currentQueue++;
		if (currentQueue == system.getN()) {
			currentQueue = 0;
		}		
	}

}
