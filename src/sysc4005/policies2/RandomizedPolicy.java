package sysc4005.policies2;

import java.util.ArrayList;

import sysc4005.SimulationSystem1;

public final class RandomizedPolicy extends AbstractPolicy2 {
	

	@Override
	public void allocateServer(int t) {
		for (int k = 0; k < system.getK(); k++) {			
			
			ArrayList<Integer> connectedQueues = new ArrayList<Integer>();
			for (int n = 0; n < system.getN(); n++) {
				
				if (system.isConnected(n, t, k)  && !system.isEmpty(n, t - 1)) {
					connectedQueues.add(n);
				}
			}		
			
			int count = connectedQueues.size();
			if (count < 1) {
				system.setServerState(t, SimulationSystem1.SERVER_IDLE, k);
				return;
			}
			int n = (int) Math.floor(system.getRandomStream().next() * count);
			
			system.setServerState(t, connectedQueues.get(n), k);	
		}
	}	
}
