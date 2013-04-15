package sysc4005.policies2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sysc4005.SimulationSystem1;

public final class LcsfLcqPolicy extends AbstractPolicy2 {

	@Override
	public void allocateServer(int t) {
		List<ServerConnectedPair> pairs = new ArrayList<ServerConnectedPair>();
		for (int k = 0; k < system.getK(); k++) {
			ServerConnectedPair pair = new ServerConnectedPair();
			pair.k = k;
			for (int n = 0; n < system.getN(); n++) {
				if (system.isConnected(n, t, k)) {
					pair.connectedCount++;
				}
			}
			pairs.add(pair);
		}
		
		Collections.sort(pairs);
		int queueOffsets[] = new int[system.getN()];
		for (ServerConnectedPair pair: pairs) {
			int k = pair.k;
			ArrayList<Integer> longestQueues = new ArrayList<Integer>();
			
			int longestLength = 0; 
			for (int n = 0; n < system.getN(); n++ ) {
				if (system.isConnected(n, t, k) && !system.isEmpty(n, t - 1, queueOffsets[n])) {
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
					queueOffsets[longestQueues.get(0)]--;
				} else {
					int n = (int) Math.floor(system.getRandomStream().next() * longestQueues.size());
					
					system.setServerState(t, longestQueues.get(n), k);
					queueOffsets[longestQueues.get(n)]--;
				}
			}
		}
	}
	
	private class ServerConnectedPair implements Comparable<ServerConnectedPair>
	{
		public int connectedCount = 0;
		public int k = -1;
		
		@Override
		public int compareTo(ServerConnectedPair other) {
			
			return Integer.compare(connectedCount, other.connectedCount);
		}
	}
}
