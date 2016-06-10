package neuralNetwork;

public class BiasPerceptron implements Perceptron {

	@Override
	public void activate() {
	}

	@Override
	public void adjustToError() {
	}

	@Override
	public double output() {
		return 1.0;
	}

	@Override
	public double error() {
		return 0;
	}

	@Override
	public void calculateError() {
	}

	@Override
	public void addInputLink(PerceptronLink l) {}

	@Override
	public void addOutputLink(PerceptronLink l) {}

}
