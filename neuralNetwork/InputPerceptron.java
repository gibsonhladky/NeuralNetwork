package neuralNetwork;

/*
 * InputPerceptron implements the Perceptron interface,
 * handling unique problems specific to the input into
 * a network.
 */
public class InputPerceptron implements Perceptron {

	private double input;
	private double output;
	
	public InputPerceptron() {
		
	}

	@Override
	public void activate() {
		output = input;
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
	
	@Override
	public double error()
	{
		return 0;
	}
	
	public void setInput(double input)
	{
		this.input = input;
	}

	@Override
	public void calculateError() {
		// do nothing
	}

}
