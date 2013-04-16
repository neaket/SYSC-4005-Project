package sysc4005.random;

public class TesStream extends AbstractRandomStream {

	private double lastRanNum;
	private double a;
	private double b;
	
	public TesStream(double a, double b){
		this.a = a;
		this.b = b;
		this.lastRanNum = -1;
	}
	
	@Override
	public double next() {
		if (lastRanNum == -1){
			lastRanNum = Math.random();
		}else{
			lastRanNum = (lastRanNum + calculateV()) % 1;
		}
		return lastRanNum;
	}

	private double calculateV(){
		return (b + a) * Math.random() + a;
	}
}
