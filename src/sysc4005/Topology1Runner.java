package sysc4005;

import sysc4005.policies.LongestConnectedQueuePolicy;
import sysc4005.policies.RandomizedPolicy;
import sysc4005.policies.RoundRobinPolicy;
import sysc4005.random.JavaRandomStream;

public class Topology1Runner {
	
	
	// p = 1
	// lambda = 0.2
	public static void runSymmetricOne() {
		int N = 5;
		int timeSlotCount = 50000;
		int iterations = 20;
		double p = 1;
		double lambda = 0.02;
		
		
		double probability[] = new double[N];
		for (int i = 0; i < N; i++) {
			probability[i] = p;
		}
		SimulationSystem system;
		system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda, new RandomizedPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-p1_lambda002_random.txt");
		
		system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda, new RoundRobinPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-p1_lambda002_roundRobin.txt");
		
		system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda, new LongestConnectedQueuePolicy(), iterations);
		
		system.runAndPrintToFile("2-2-p1_lambda002_LCQ.txt");
			
	}	
	
	// p = 1
	// lambda = 0.2 * i, i = 1,2,...,10
	public static void runSymmetricOneFull() {
		int N = 5;
		int timeSlotCount = 50000;
		int iterations = 20;
		double p = 1;
		double lambda = 0.02;
		
		double probability[] = new double[N];
		for (int i = 0; i < N; i++) {
			probability[i] = p;
		}
		
		for (int i = 1; i < 10; i++) {
			SimulationSystem system;
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-p1_full_lambda002_random.txt");
			
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-p1_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new LongestConnectedQueuePolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-p1_full_lambda002_LCQ.txt");
		}
			
	}
	
	// p = .8
	// lambda = 0.2 * i, i = 1,2,...,10
	public static void runSymmetricTwoFull() {
		int N = 5;
		int timeSlotCount = 50000;
		int iterations = 20;
		double p = .8;
		double lambda = 0.02;
		
		double probability[] = new double[N];
		for (int i = 0; i < N; i++) {
			probability[i] = p;
		}
		
		for (int i = 1; i < 10; i++) {
			SimulationSystem system;
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-p1_full_lambda002_random.txt");
			
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-p1_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new LongestConnectedQueuePolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-p1_full_lambda002_LCQ.txt");
		}
			
	}	
	
	// p = .2
	// lambda = 0.014 * i, i = 1,2,...,10
	public static void runSymmetricThreeFull() {
		int N = 5;
		int timeSlotCount = 50000;
		int iterations = 20;
		double p = .2;
		double lambda = 0.014;
		
		double probability[] = new double[N];
		for (int i = 0; i < N; i++) {
			probability[i] = p;
		}
		
		for (int i = 1; i < 10; i++) {
			SimulationSystem system;
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-p1_full_lambda002_random.txt");
			
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new RoundRobinPolicy(), iterations);
			system.runAndPrintToFile("2-2-THREE-p1_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambda * i, new LongestConnectedQueuePolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-p1_full_lambda002_LCQ.txt");
		}
			
	}
}
