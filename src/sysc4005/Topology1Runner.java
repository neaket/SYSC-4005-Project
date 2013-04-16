package sysc4005;

import sysc4005.policies1.LcqPolicy;
import sysc4005.policies1.RandomizedPolicy;
import sysc4005.policies1.RoundRobinPolicy;
import sysc4005.random.JavaRandomStream;
import sysc4005.random.TesStream;

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
		double lambdas[] = new double[N];
		for (int i = 0; i < N; i++) {
			probability[i] = p;
			lambdas[i] = lambda;
		}
		
		SimulationSystem1 system;
		system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-p1_lambda002_random.txt");
		
		system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-p1_lambda002_roundRobin.txt");
		
		system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-p1_lambda002_LCQ.txt");
		
		// TES generation
		system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-TES_high_lambda002_random.txt");
		
		system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-TES_high_lambda002_roundRobin.txt");
		
		system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-TES_high_lambda002_LCQ.txt");
		
		
		system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-TES_low_lambda002_random.txt");
		
		system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-TES_low_lambda002_roundRobin.txt");
		
		system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);
		
		system.runAndPrintToFile("2-2-TES_low_lambda002_LCQ.txt");
			
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
			double lambdas[] = new double[N];
			for (int n = 0; n < N; n++) {
				lambdas[n] = lambda * i;
			}
			
			SimulationSystem1 system;
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-p1_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-p1_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-p1_full_lambda002_LCQ.txt");
			
			//TES

			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-TES_high_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-TES_high_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-TES_high_full_lambda002_LCQ.txt");

			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-TES_low_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-TES_low_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ONE-TES_low_full_lambda002_LCQ.txt");
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
			double lambdas[] = new double[N];
			for (int n = 0; n < N; n++) {
				lambdas[n] = lambda * i;
			}
			
			SimulationSystem1 system;
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-p1_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-p1_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-p1_full_lambda002_LCQ.txt");
			
			//TES
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-TES_high_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-TES_high_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-TES_high_full_lambda002_LCQ.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-TES_low_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-TES_low_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TWO-TES_low_full_lambda002_LCQ.txt");
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
			double lambdas[] = new double[N];
			for (int n = 0; n < N; n++) {
				lambdas[n] = lambda * i;
			}
			
			SimulationSystem1 system;
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-p1_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);
			system.runAndPrintToFile("2-2-THREE-p1_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-p1_full_lambda002_LCQ.txt");
			
			// TES
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-TES_high_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-TES_high_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-TES_high_full_lambda002_LCQ.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-TES_low_full_lambda002_random.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-TES_low_full_lambda002_roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-THREE-TES_low_full_lambda002_LCQ.txt");
		}
			
	}
	
	// p1 = 1, p2 = 0.8, p3 = 0.6, p4 = 0.4, p5 = 0.2
	// lambda = 0.006 * i
	// lambda_n = n * lambda
	public static void runAsymmetricFull() {
		int N = 5;
		int timeSlotCount = 50000;
		int iterations = 20;
		double lambda = 0.006;
		
		double probability[] = new double[N];
		probability[0] = 1;
		probability[1] = .8;
		probability[2] = .6;
		probability[3] = .4;
		probability[4] = .2;
		
		for (int i = 1; i < 10; i++) {
			
			double lambdas[] = new double[N];
			for (int n = 0; n < N; n++) {
				lambdas[n] = (n + 1) * lambda * i;
			}
			
			SimulationSystem1 system;
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ASYMETRIC-random.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);
			system.runAndPrintToFile("2-2-ASYMETRIC-roundRobin.txt");
			
			system = new SimulationSystem1(new JavaRandomStream(), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-ASYMETRIC-LCQ.txt");
			
			//TES
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TES_high-ASYMETRIC-random.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);
			system.runAndPrintToFile("2-2-TES_high-ASYMETRIC-roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.4, 0.4), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TES_high-ASYMETRIC-LCQ.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TES_low-ASYMETRIC-random.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new RoundRobinPolicy(), iterations);
			system.runAndPrintToFile("2-2-TES_low-ASYMETRIC-roundRobin.txt");
			
			system = new SimulationSystem1(new TesStream(0.1, 0.1), timeSlotCount, probability, lambdas, new LcqPolicy(), iterations);			
			system.runAndPrintToFile("2-2-TES_low-ASYMETRIC-LCQ.txt");
		}
			
	}
	
}
