package sysc4005;


import sysc4005.policies2.AsLcqPolicy;
import sysc4005.policies2.LcsfLcqPolicy;
import sysc4005.policies2.RandomizedPolicy;
import sysc4005.random.JavaRandomStream;

public class Topology2Runner {
	
	// p = .5
	// lambda = 0.02 * i, i = 1,2..,10
	// N = 5, K = 3, lambda_n = n * lambda, 
	public static void runOne() {
		int N = 5;
		int K = 3;
		int timeSlotCount = 50000;
		int iterations = 20;
		double p = .5;
		double lambda = 0.02;
		
		double probability[][] = new double[N][K];
		
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < K; k++) {
				probability[i][k] = p;
			}
		}
		
		for (int i = 1; i < 10; i++) {
			double lambdas[] = new double[N];
			for (int n = 0; n < N; n++) {
				lambdas[n] = (n + 1) * lambda * i;
			}
			
			
			SimulationSystem2 system;
			system = new SimulationSystem2(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RandomizedPolicy(), iterations, K);			
			system.runAndPrintToFile("3-2-ONE_random.txt");
			
			system = new SimulationSystem2(new JavaRandomStream(), timeSlotCount, probability, lambdas, new AsLcqPolicy(), iterations, K);
			system.runAndPrintToFile("3-2-ONE_asLcq.txt");
			
			system = new SimulationSystem2(new JavaRandomStream(), timeSlotCount, probability, lambdas, new LcsfLcqPolicy(), iterations, K);			
			system.runAndPrintToFile("3-2-ONE_lcsfLcq.txt");
		}
			
	}
	
}
