package neuralNetwork;

public class OutputPerceptron implements Perceptron {
	
	private InputLinks inputLinks;
	
	private double output;
	private double expectedOutput;
	private double error;
	
	public OutputPerceptron()
	{
		this(new RandomWeightGenerator());
	}
	
	public OutputPerceptron(WeightGenerator wg) 
	{
		inputLinks = new InputLinks(wg);
	}

	@Override
	public void activate() 
	{
		output = Perceptron.activationFunction(inputValue());
	}

	@Override
	public void adjustToError() 
	{
		error = calculateError();
		inputLinks.adjustToErrorGivenOutput(error, output);
	}

	@Override
	public double output() 
	{
		return output;
	}
	
	@Override
	public double error()
	{
		return error;
	}
	
	public void setExpectedOutput(double newExpectedOutput)
	{
		expectedOutput = newExpectedOutput;
	}
	
	public void addInput(Perceptron p)
	{
		inputLinks.addLinkFrom(p);
	}
	
	private double inputValue()
	{
		return inputLinks.inputValue();
	}
	
	private double calculateError()
	{
		return expectedOutput - output;
	}

}
