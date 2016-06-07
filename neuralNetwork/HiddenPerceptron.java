package neuralNetwork;

public class HiddenPerceptron implements Perceptron
{
	private double output;
	private double error;
	
	private InputLinks inputLinks;
	private OutputLink outputLinks;
	
	
	public HiddenPerceptron(WeightGenerator wg)
	{
		if(wg == null)
		{
			throw new IllegalArgumentException("Cannot have a null weight generator.");
		}
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
		if(p == null)
		{
			throw new IllegalArgumentException("Cannot add input from a null.");
		}
		inputLinks.addLinkFrom(p);
	}
	
	public void addOutputLink(Perceptron p)
	{
		if(p == null)
		{
			throw new IllegalArgumentException("Cannot add output to a null.");
		}
		outputLinks.addLink(p);
	}
}
