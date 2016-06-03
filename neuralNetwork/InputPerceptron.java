package neuralNetwork;

/*
 * InputPerceptron implements the Perceptron interface,
 * handling unique problems specific to the input into
 * a network.
 */
public class InputPerceptron implements Perceptron {

	private double input;
	private double output;
	
	public InputPerceptron(int index) {
		
	}

	@Override
	public void activate() {
		output = input;
	}

	@Override
	public void calculateDeltas() {
		// Do nothing
	}

	@Override
	public void adjustToError() {
		// do nothing
	}

	@Override
	public double output()
	{
		return output;
	}
	
	public void setInput(double input)
	{
		this.input = input;
	}

}
