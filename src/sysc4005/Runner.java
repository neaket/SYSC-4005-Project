package sysc4005;

import sysc4005.policies.LongestConnectedQueuePolicy;
import sysc4005.policies.RandomizedPolicy;
import sysc4005.policies.RoundRobinPolicy;
import sysc4005.random.JavaRandomStream;

public class Runner {
	
	
	// p = 1
	// lambda = 0.2
	public static void runSymmetricOne() {
		int N = 5;
		int timeSlotCount = 50000;
		double p = 1;
		double lambda = 0.02;
		
		double probability[] = new double[N];
		for (int i = 0; i < N; i++) {
			probability[i] = p;
		}
		SimulationSystem system;
		system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda, new RandomizedPolicy());
		system.simulateSystem();
		system.printToFile("2-2-p1_lambda002_random.txt");
		
		system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda, new RoundRobinPolicy());
		system.simulateSystem();
		system.printToFile("2-2-p1_lambda002_roundRobin.txt");
		
		system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda, new LongestConnectedQueuePolicy());
		system.simulateSystem();
		system.printToFile("2-2-p1_lambda002_LCQ.txt");
			
	}	
}
