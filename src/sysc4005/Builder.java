package sysc4005;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import sysc4005.policies.RandomizedPolicy;
import sysc4005.random.JavaRandomStream;

public class Builder {
	
	
	// p = 1
	// lambda = 0.2
	public static void buildSymmetricOne() {
		int N = 5;
		int timeSlotCount = 10;
		double p = 1;
		double lambda = 0.02;
		
		double probability[] = new double[N];
		for (int i = 0; i < N; i++) {
			probability[i] = p;
		}
		
		double lambdas[] = new double[timeSlotCount];
		for (int i = 0; i < timeSlotCount; i++) {
			lambdas[i] = lambda;
		}
		
		
		SimulationSystem system = new SimulationSystem(new JavaRandomStream(), timeSlotCount, probability, lambdas, new RandomizedPolicy());
		system.simulateSystem();
		
		PrintWriter writer = null; 
		try {
			writer = new PrintWriter("2-2-p1_lambda002.txt", "UTF-8");
			system.printToFile(writer);
			
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
