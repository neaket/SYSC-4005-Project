package sysc4005.policies;

import sysc4005.SimulationSystem;

public abstract class AbstractPolicy {

	protected SimulationSystem system;	
	
	public void setSimulationSystem(SimulationSystem system) {
		this.system = system;
	}
	
	/**
	 * Runs the scheduling policy on the specified time slot.
	 * 
	 * @param t The time slot number
	 */
	public abstract void allocateServer(int t);
}
