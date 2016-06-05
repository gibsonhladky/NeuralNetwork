package neuralNetwork;

public class OutputPerceptron implements Perceptron {

	private final int inputSize;
	private final double LEARNING_RATE = 0.10;
	
	private double[] inputs;
	private double[] weights;
	
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
		
		inputSize = 0;
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
		adjustWeightsBy(error);
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
	
	public void setInputs(double[] newInputs)
	{
		if(newInputs.length != inputSize)
		{
			throw new IllegalArgumentException("Inputs must match input size of the perceptron.");
		}
		this.inputs = newInputs;
	}
	
	public void setWeights(double[] newWeights)
	{
		if(newWeights.length != inputSize)
		{
			throw new IllegalArgumentException("Inputs must match input size of the perceptron.");
		}
		weights = newWeights;
	}
	
	public void setExpectedOutput(double newExpectedOutput)
	{
		expectedOutput = newExpectedOutput;
	}
	
	private double inputValue()
	{
		return inputLinks.inputValue();
	}
	
	private double calculateError()
	{
		return expectedOutput - output;
	}
	
	private void adjustWeightsBy(final double error)
	{
		for(int i = 0; i < inputSize; i++)
		{
			weights[i] += LEARNING_RATE * error * output;
		}
	}

}
