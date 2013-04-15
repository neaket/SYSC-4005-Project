package sysc4005.policies1;

import java.util.ArrayList;

import sysc4005.SimulationSystem1;

public final class LcqPolicy extends AbstractPolicy1 {

	@Override
	public void allocateServer(int t) {
		ArrayList<Integer> longestQueues = new ArrayList<Integer>();
		
		int longestLength = 0; 
		for (int n = 0; n < system.getN(); n++ ) {
			if (system.isConnected(n, t) && !system.isEmpty(n, t - 1)) {
				int length = system.getQueueLength(n, t - 1);
				if (length > longestLength) {
					longestQueues.clear();
					longestQueues.add(n);
					longestLength = length;
				} else if (length == longestLength) {
					longestQueues.add(n);
				}
			}
		}
		
		if (longestLength == 0) {
			system.setServerState(t, SimulationSystem1.SERVER_IDLE);
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
