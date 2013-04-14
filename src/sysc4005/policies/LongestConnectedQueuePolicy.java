package sysc4005.policies;

import java.util.ArrayList;

import sysc4005.SimulationSystem;

public final class LongestConnectedQueuePolicy extends AbstractPolicy {

	@Override
	public void allocateServer(int t) {
		ArrayList<Integer> longestQueues = new ArrayList<Integer>();
		
		int longestLength = 0; 
		for (int n = 0; n < system.getN(); n++ ) {
			if (system.isConnected(n, t) && !system.isEmpty(n, t)) {
				int length = system.getQueueLength(n, t);
				if (length > longestLength) {
					longestQueues.clear();
					longestQueues.add(n);
				} else if (length == longestLength) {
					longestQueues.add(n);
				}
			}
		}
		
		if (longestLength == 0) {
			system.setServerState(t, SimulationSystem.SERVER_IDLE);
		} else {
			if (longestQueues.size() == 1) {
				system.setServerState(t, longestQueues.get(0));
			} else {
				int n = (int) Math.floor(system.getRandomStream().next() * longestQueues.size());
				
				system.setServerState(t, longestQueues.get(n));	
			}
		}		
	}
}
