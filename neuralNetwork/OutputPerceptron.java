package neuralNetwork;

public class OutputPerceptron implements Perceptron {

	int inputSize;
	
	double[] inputs;
	double[] weights;
	
	double output;
	
	public OutputPerceptron(int inputSize) {
		this.inputSize = inputSize;
	}

	@Override
	public void activate() {
		output = Perceptron.activationFunction(inputValue());
	}

	@Override
	public void calculateDeltas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWeights() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double output() {
		return output;
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
	
	private double inputValue()
	{
		double sumOfWeightedInputs = 0;
		for(int i = 0; i < inputSize; i++)
		{
			sumOfWeightedInputs += inputs[i] * weights[i];
		}
		return sumOfWeightedInputs;
	}

}
