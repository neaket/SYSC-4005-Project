package sysc4005.random;

public abstract class AbstractRandomStream {
	/**
	 * @return A random number from the stream, ranging from 0 to less than 1.0
	 */
	public abstract double next();
}
