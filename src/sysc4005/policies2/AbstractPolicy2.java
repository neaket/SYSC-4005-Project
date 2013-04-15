package sysc4005.policies2;

import sysc4005.SimulationSystem2;

public abstract class AbstractPolicy2 {

	protected SimulationSystem2 system;	
	
	public void setSimulationSystem(SimulationSystem2 system) {
		this.system = system;
	}
	
	/**
	 * Runs the scheduling policy on the specified time slot.
	 * 
	 * @param t The time slot number
	 */
	public abstract void allocateServer(int t);
}
