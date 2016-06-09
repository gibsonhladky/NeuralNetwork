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
		if(wg == null)
		{
			throw new IllegalArgumentException("Cannot have a null weight generator.");
		}
		inputLinks = new InputLinks(this, wg);
	}

	@Override
	public void activate() 
	{
		output = Perceptron.activationFunction(inputLinks.totalInput());
	}
	
	@Override
	public void calculateError()
	{
		error =  expectedOutput - output;
	}

	@Override
	public void adjustToError() 
	{
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
	
	public void addInputLink(PerceptronLink l)
	{
		if(l == null)
		{
			throw new IllegalArgumentException("Cannot add a null input.");
		}
		inputLinks.addLink(l);
	}
	

}
