package sysc4005.policies2;

import java.util.ArrayList;

import sysc4005.SimulationSystem1;

public final class AsLcqPolicy extends AbstractPolicy2 {

	@Override
	public void allocateServer(int t) {
		for (int k = 0; k < system.getK(); k++) {
			ArrayList<Integer> longestQueues = new ArrayList<Integer>();
			
			int longestLength = 0; 
			for (int n = 0; n < system.getN(); n++ ) {
				if (system.isConnected(n, t, k) && !system.isEmpty(n, t - 1)) {
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
				system.setServerState(t, SimulationSystem1.SERVER_IDLE, k);
			} else {
				if (longestQueues.size() == 1) {
					system.setServerState(t, longestQueues.get(0), k);
				} else {
					int n = (int) Math.floor(system.getRandomStream().next() * longestQueues.size());
					
					system.setServerState(t, longestQueues.get(n), k);	
				}
			}
		}
	}
}
