package sysc4005.policies;

import sysc4005.SystemData;

public abstract class AbstractPolicy {

	protected SystemData data;	
	
	public AbstractPolicy(SystemData data) {
		this.data = data;
	}
	
	/**
	 * @param t Timeslot
	 */
	public abstract void allocateServer(int t);
}
