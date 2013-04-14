package sysc4005;

public class Builder {
	
	
	// p = 1
	// lambda = 0.2
	public void buildSymmetricOne() {
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
		
		
		SystemData data = new SystemData(10, probability, lambdas);
	}

	
	

	
	
}
