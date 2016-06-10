package neuralNetwork;

/*
 * InputPerceptron implements the Perceptron interface,
 * handling unique problems specific to the input into
 * a network.
 */
public class InputPerceptron implements Perceptron {
	
	private double output;

	@Override
	public void activate() {
		// Do nothing - output is only changed by setting input
	}

	@Override
	public void adjustToError() {
		// Do nothing
	}

	@Override
	public double output()
	{
		return output;
	}
	
	@Override
	public double error()
	{
		return 0;
	}

	@Override
	public void calculateError() {
		// Do nothing
	}

	@Override
	public void addInputLink(PerceptronLink l) {
		// Do nothing
	}

	@Override
	public void addOutputLink(PerceptronLink l) {
		// Do nothing
	}
	
	public void setInput(double input)
	{
		this.output = input;
	}

}
