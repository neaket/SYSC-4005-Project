package sysc4005;

public class SystemData {
	public final static int SERVER_IDLE = -1;
	
	private int N = 5;
	private int serverStates[];
	private double probability[];
	private double lambda[];
	private int[][] queueLength;
	private int timeSlotCount;
	
	public SystemData(int timeSlotCount, double probability[], double lambda[]) {
		assert(N == probability.length);
		assert(timeSlotCount == lambda.length);
		
		this.timeSlotCount = timeSlotCount;		
		this.probability = probability;
		this.lambda = lambda;
		
		serverStates = new int[timeSlotCount];
		queueLength = new int[N][timeSlotCount];		
	}
	
	
	public int getN() {
		return N;
	}

	public double getProbability(int n) {
		return probability[n];
	}
	
	public boolean isConnected(int n, int t) {
		return false;
	}
	
	public void setServerState(int t, int state) {
		this.serverStates[t] = state;
	}


	public boolean isEmpty(int n, int t) {
		return getQueueLength(n, t) == 0;
	}


	public int getQueueLength(int n, int t) {
		return queueLength[n][t];
	}
	
	public double getAverageQueueOccupancy(int n) {
		double total = 0;
		for (int t = 0; t < timeSlotCount; t++) {
			total += queueLength[n][t];
		}		
		return total / timeSlotCount;
	}
}
