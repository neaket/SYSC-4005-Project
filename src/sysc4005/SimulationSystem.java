package sysc4005;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import sysc4005.generators.BernoulliGenerator;
import sysc4005.policies.AbstractPolicy;
import sysc4005.random.AbstractRandomStream;

public class SimulationSystem {
	public final static int SERVER_IDLE = -1;
	
	private int N = 5;
	private int serverStates[];
	private double probability[];
	private double lambda;
	private int[][] queueLength;
	private int timeSlotCount;
	private AbstractRandomStream stream;
	private int currentTimeSlot = 0;
	private AbstractPolicy policy;
	private boolean[][] queueIsconnected;
	
	private int debugTotalAn = 0;
	
	public SimulationSystem(AbstractRandomStream stream, int timeSlotCount, double probability[], double lambda, AbstractPolicy policy) {
		assert(N == probability.length);
		
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
		
		//System.out.println("Server state at " + t + " is " + state);
	}


	public boolean isEmpty(int n, int t) {
		if (t < 0) return true;
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
			int Hn = 0;
			if (currentTimeSlot > 0) {
				Xn = queueLength[n][currentTimeSlot - 1];
				
				if (serverStates[currentTimeSlot] == n && getQueueLength(n, currentTimeSlot - 1) > 0) {
					Hn = 1;
				}
			}	
			
			int An = new BernoulliGenerator(stream, lambda).next();
			debugTotalAn += An;
			
			 
			queueLength[n][currentTimeSlot] = Xn - Hn + An;
		}			
		
		//System.out.println("Queue Lengths " + queueLength[0][currentTimeSlot] + " " + queueLength[1][currentTimeSlot] + " " + queueLength[2][currentTimeSlot] + " " + queueLength[3][currentTimeSlot] + " " + queueLength[4][currentTimeSlot]);
		currentTimeSlot++;
	}


	public int getQueueLength(int n, int t) {
		return queueLength[n][t];
	}
	
	private double getAverageQueueOccupancy() {
		double total = 0;
		for (int n = 0; n < N; n++) {
			for (int t = 0; t < timeSlotCount; t++)
			total += queueLength[n][t];
		}		
		return total / (N * timeSlotCount);
	}


	public AbstractRandomStream getRandomStream() {
		return stream;
	}
	
	public void printToFile(String fileName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println("lambda = " + lambda);
			//writer.println("debug An = " + debugTotalAn);
			writer.println("Average = " + getAverageQueueOccupancy());
		
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		
	}
}
