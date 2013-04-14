package sysc4005;

public class SystemData {
	public final static int SERVER_IDLE = -1;
	
	private int N = 5;
	private int serverStates[];
	private double probability[] = new double[N];
	
	public SystemData(int timeSlotCount) {
		serverStates = new int[timeSlotCount];
	}
	
	
	public int getN() {
		return N;
	}

	public double getProbabilityN(int n) {
		return probability[n];
	}
	
	public boolean isConnected(int n, int t) {
		return false;
	}
	
	public void setServerState(int t, int state) {
		this.serverStates[t] = state;
	}


	public boolean isEmpty(int n, int t) {
		// TODO Auto-generated method stub
		return false;
	}


	public int getQueueLength(int i, int t) {
		// TODO Auto-generated method stub
		return 0;
	}
}
