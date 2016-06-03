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
	public void updateWeights() {
		// do nothing
	}
	
	public void setInput(double input)
	{
		this.input = input;
	}
	
	public double output()
	{
		return output;
	}

}
