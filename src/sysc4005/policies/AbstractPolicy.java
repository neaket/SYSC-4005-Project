package sysc4005.policies;

import sysc4005.SimulationSystem;

public abstract class AbstractPolicy {

	protected SimulationSystem system;	
	
	public void setSimulationSystem(SimulationSystem system) {
		this.system = system;
	}
	
	/**
	 * @param t Timeslot
	 */
	public abstract void allocateServer(int t);
}
