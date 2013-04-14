package policies;

import sysc4005.SystemData;

public abstract class AbstractPolicy {

	protected SystemData data;
	
	
	public AbstractPolicy(SystemData data) {
		this.data = data;
	}
	
	public abstract void allocateServer(int t);

}
