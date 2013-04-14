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
	
	
	
	/**
	 * Construct the Simulation System.
	 * 
	 * @param stream A stream of random numbers
	 * @param timeSlotCount The maximum time slots
	 * @param probability The probability that a queue is connected to the server
	 * @param lambda The parameter in bernoulli used to determine how often new tasks are added to a queue 
	 * @param policy The scheduling policy
	 */
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
	
	
	/**
	 * @return The number of queues in the system
	 */
	public int getN() {
		return N;
	}

	/**
	 * @param n The queue index
	 * @return The probability that queue n is connected.
	 */
	private double getProbability(int n) {
		return probability[n];
	}
	
	/**
	 * @param n The queue index
	 * @param t The time slot number
	 * @return True if the queue is connected to the server at the time slot; otherwise false
	 */
	public boolean isConnected(int n, int t) {
		return queueIsconnected[n][t];
	}
	
	/**
	 * @param t The time slot number
	 * @param state The state, either SERVER_IDLE or the index of the queue that is connected
	 */
	public void setServerState(int t, int state) {
		this.serverStates[t] = state;		
		//System.out.println("Server state at " + t + " is " + state);
	}


	/**
	 * @param n The index of the queue
	 * @param t The time slot number
	 * @return True if the queue is empty at the time slot, otherwise false
	 */
	public boolean isEmpty(int n, int t) {
		if (t < 0) return true;
		return getQueueLength(n, t) == 0;
	}
	
	/**
	 * Simulates the system.  Note this method can only be called once.
	 */
	public void simulateSystem() {
		for (int t = 0; t < timeSlotCount; t++) {
			advanceTimeSlot();
		}
	}
	
	/**
	 * Advances the simulation to the next timeslot.
	 */
	private void advanceTimeSlot() {
		assert(currentTimeSlot < timeSlotCount);
		
		// calculate Cn
		for (int n = 0; n < N; n++) {
			queueIsconnected[n][currentTimeSlot] = new BernoulliGenerator(stream, getProbability(n)).next() == 1;			
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
			//debugTotalAn += An;
			
			// equation found in section 2.1 
			queueLength[n][currentTimeSlot] = Xn - Hn + An;
		}			
		
		//System.out.println("Queue Lengths " + queueLength[0][currentTimeSlot] + " " + queueLength[1][currentTimeSlot] + " " + queueLength[2][currentTimeSlot] + " " + queueLength[3][currentTimeSlot] + " " + queueLength[4][currentTimeSlot]);
		currentTimeSlot++;
	}


	/**
	 * @param n The queue index
	 * @param t The time slot number
	 * @return The length of queue n at time slot t.
	 */
	public int getQueueLength(int n, int t) {
		return queueLength[n][t];
	}
	
	/**
	 * @return The average queue length of all queues for the entire simulation.
	 */
	private double getAverageQueueOccupancy() {
		double total = 0;
		for (int n = 0; n < N; n++) {
			for (int t = 0; t < timeSlotCount; t++)
			total += queueLength[n][t];
		}		
		return total / (N * timeSlotCount);
	}


	/**
	 * @return The random number stream used for this simulation
	 */
	public AbstractRandomStream getRandomStream() {
		return stream;
	}
	
	/**
	 * @param Prints the simulation results to the file specified.
	 */
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
