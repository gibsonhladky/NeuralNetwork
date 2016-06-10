import neuralNetwork.Perceptron;
import neuralNetwork.PerceptronLink;

/*
 * A dummy object to test interactions with Perceptrons.
 * MockPerceptron will always return 0.5 from output() and
 * error(), and do nothing else.
 */
public class MockPerceptron implements Perceptron {

	@Override
	public void activate() {}

	@Override
	public void adjustToError() {}

	@Override
	public double output() {
		return 0.5;
	}

	@Override
	public double error() {
		return 0.5;
	}

	@Override
	public void calculateError() {}

	@Override
	public void addInputLink(PerceptronLink l) {}

	@Override
	public void addOutputLink(PerceptronLink l) {}

}
