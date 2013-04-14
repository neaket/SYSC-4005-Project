package sysc4005.policies;

import java.util.ArrayList;

import sysc4005.SimulationSystem;

public final class RandomizedPolicy extends AbstractPolicy {
	

	@Override
	public void allocateServer(int t) {
		ArrayList<Integer> connectedQueues = new ArrayList<Integer>();
		for (int n = 0; n < system.getN(); n++) {
			if (system.isConnected(n, t)  && !system.isEmpty(n, t - 1)) {
				connectedQueues.add(n);
			}
		}		
		
		int count = connectedQueues.size();
		if (count < 1) {
			system.setServerState(t, SimulationSystem.SERVER_IDLE);
			return;
		}
		int n = (int) Math.floor(system.getRandomStream().next() * count);
		
		system.setServerState(t, connectedQueues.get(n));		
	}	
}
