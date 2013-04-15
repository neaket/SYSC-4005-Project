package sysc4005.policies1;

import sysc4005.SimulationSystem1;

public abstract class AbstractPolicy1 {

	protected SimulationSystem1 system;	
	
	public void setSimulationSystem(SimulationSystem1 system) {
		this.system = system;
	}
	
	/**
	 * Runs the scheduling policy on the specified time slot.
	 * 
	 * @param t The time slot number
	 */
	public abstract void allocateServer(int t);
}
