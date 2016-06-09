package neuralNetwork;

/*
 * HiddenPerceptron is an implementation of Perceptron
 * that deals with absolutely no strict input or output.
 * The HiddenPerceptron provides output based on its inputs
 * and updates based on its outputs.
 */
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
	 * Converts the inputs of the Hidden Perceptron to an
	 * output value.
	 */
	@Override
	public void activate()
	{
		output = Perceptron.activationFunction(inputLinks.inputValue());
	}
	
	@Override
	public void calculateError()
	{
		error = outputLinks.getTotalError();
	}
	
	@Override
	public void adjustToError()
	{
		inputLinks.adjustToErrorGivenOutput(error, output);
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
