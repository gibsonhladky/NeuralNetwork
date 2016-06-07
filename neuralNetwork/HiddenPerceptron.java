package neuralNetwork;

public class HiddenPerceptron implements Perceptron
{
	private double output;
	private double error;
	
	private InputLinks inputLinks;
	private OutputLink outputLinks;
	
	
	public HiddenPerceptron(WeightGenerator wg)
	{
		inputLinks = new InputLinks(wg);
		outputLinks = new OutputLink(wg);
	}

	/*
	 * Calculates a new activation value based on inputs and weights.
	 * Does nothing for input layer perceptrons
	 */
	@Override
	public void activate()
	{
		output = Perceptron.activationFunction(inputLinks.inputValue());
	}
	
	/*
	 * Update the weights the perceptron with the current delta values.
	 */
	@Override
	public void adjustToError()
	{
		error = outputLinks.getAssociatedError();
	}

	@Override
	public double output() {
		return output;
	}

	@Override
	public double error() 
	{
		return error;
	}
	
	public void addInputLink(Perceptron p)
	{
		inputLinks.addLinkFrom(p);
	}
	
	public void addOutputLink(Perceptron p)
	{
		outputLinks.addLink(p);
	}
}
