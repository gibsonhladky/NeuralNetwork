import neuralNetwork.Perceptron;

/*
 * A dummy object to test interactions with Perceptrons.
 * MockPerceptron will always return 0.5 from output() and
 * error(), and do nothing else.
 */
public class MockPerceptron implements Perceptron {

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void adjustToError() {
		// TODO Auto-generated method stub

	}

	@Override
	public double output() {
		// TODO Auto-generated method stub
		return 0.5;
	}

	@Override
	public double error() {
		// TODO Auto-generated method stub
		return 0.5;
	}

	@Override
	public void calculateError() {
		// TODO Auto-generated method stub
		
	}

}
