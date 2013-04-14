package sysc4005.generators;

import sysc4005.random.AbstractRandomStream;

public class BernoulliGenerator {
	
	private AbstractRandomStream random;
	private double p;

	public BernoulliGenerator(AbstractRandomStream random, double p) {
		this.random = random;
		this.p = p;
	}
	
	public int next() {
		if (random.next() < p) {
			return 1;
		}
		return 0;
	}
}
