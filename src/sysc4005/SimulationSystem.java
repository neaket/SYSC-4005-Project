package sysc4005;

import java.io.PrintWriter;

import sysc4005.generators.BernoulliGenerator;
import sysc4005.policies.AbstractPolicy;
import sysc4005.random.JavaRandomStream;
import sysc4005.random.AbstractRandomStream;

public class SimulationSystem {
	public final static int SERVER_IDLE = -1;
	
	private int N = 5;
	private int serverStates[];
	private double probability[];
	private double lambda[];
	private int[][] queueLength;
	private int timeSlotCount;
	private AbstractRandomStream stream;
	private int currentTimeSlot = 0;
	private AbstractPolicy policy;
	private boolean[][] queueIsconnected;
	
	public SimulationSystem(AbstractRandomStream stream, int timeSlotCount, double probability[], double lambda[], AbstractPolicy policy) {
		assert(N == probability.length);
		assert(timeSlotCount == lambda.length);
		
		this.stream = stream;
		this.timeSlotCount = timeSlotCount;		
		this.probability = probability;
		this.lambda = lambda;
		this.policy = policy;
		this.policy.setSimulationSystem(this);
		
		serverStates = new int[timeSlotCount];
		queueLength = new int[N][timeSlotCount];		
		queueIsconnected = new boolean[N][timeSlotCount];
	}
	
	
	public int getN() {
		return N;
	}

	public double getProbability(int n) {
		return probability[n];
	}
	
	public boolean isConnected(int n, int t) {
		return queueIsconnected[n][t];
	}
	
	public void setServerState(int t, int state) {
		this.serverStates[t] = state;
	}


	public boolean isEmpty(int n, int t) {
		return getQueueLength(n, t) == 0;
	}
	
	public void simulateSystem() {
		for (int t = 0; t < timeSlotCount; t++) {
			advanceTimeSlot();
		}
	}
	
	private void advanceTimeSlot() {
		assert(currentTimeSlot < timeSlotCount);
		
		// calculate Cn
		for (int n = 0; n < N; n++) {
			queueIsconnected[n][currentTimeSlot] = new BernoulliGenerator(stream, probability[n]).next() == 1;			
		}		
		
		policy.allocateServer(currentTimeSlot);
		
		for (int n = 0; n < N; n++) {
			int Xn = 0;			
			if (currentTimeSlot > 0) {
				Xn = queueLength[n][currentTimeSlot - 1];
			}
			
			int Hn = 0;
			if (serverStates[currentTimeSlot] == n && getQueueLength(n, currentTimeSlot) > 0) {
				Hn = 1;
			}
			
			int An = new BernoulliGenerator(stream, lambda[currentTimeSlot]).next();
			 
			queueLength[n][currentTimeSlot] = Xn - Hn + An;
		}			
		
		currentTimeSlot++;
	}


	public int getQueueLength(int n, int t) {
		return queueLength[n][t];
	}
	
	private double getAverageQueueOccupancy(int t) {
		double total = 0;
		for (int n = 0; t < N; t++) {
			total += queueLength[n][t];
		}		
		return total / N;
	}


	public AbstractRandomStream getRandomStream() {
		return stream;
	}
	
	public void printToFile(PrintWriter writer) {
		writer.println("i, lambda, averageQueueOccupancy");
		for (int t = 0; t < timeSlotCount; t++) {			
			writer.println(t + ", " + lambda[t] + ", " + getAverageQueueOccupancy(t));
		}
	}
}
