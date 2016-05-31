import java.util.ArrayList;
import java.util.Random;

public class Perceptron
{	
	public static final double LEARNING_RATE = 0.1;
	public static final Random randGen = new Random();
	
	public int index; // current perceptron's index with it's layer
	public ArrayList<Perceptron> outputs; // next layer of perceptrons
	public ArrayList<Perceptron> inputs; // previous layer of perceptrons
	
	public boolean inputLayer;
	public double activationValue;
	public ArrayList<Double> inputWeights;
	public double bias;
	
	public double delta;
		
	public Perceptron(int index, ArrayList<Perceptron> inputs)
	{
		if(index < 0)
		{
			throw new IllegalArgumentException("Negative Perceptron index: " + index);
		}
		
		this.index = index;
		bias = randGen.nextDouble();
		if(randGen.nextBoolean())
		{
			bias *= -1;
		}
		
		if(inputs != null)
		{
			this.inputs = inputs;
			int size = inputs.size();
			inputWeights = new ArrayList<Double>(size);
			for(int i = 0; i < size; i++)
			{
				double weight = randGen.nextDouble();
				if(randGen.nextBoolean())
				{
					weight *= -1;
				}
				inputWeights.add(weight);
			}
		}
		else
		{
			inputLayer = true;
			inputWeights = new ArrayList<Double>();
		}
		this.outputs = null;
	}
	
	public void activate(double[] inputs)
	{
		activationValue = logisticalFunction(bias + inputs[index]);
	}

	/*
	 * Calculates a new activation value based on inputs and weights.
	 * Does nothing for input layer perceptrons
	 */
	public void activate()
	{
		if(inputLayer)
		{
			return;
		}
		
		activationValue = logisticalFunction(bias + sumOfWeightedInputs());
	}
	
	/*
	 * Returns the sum of all inputs times their weight
	 */
	private double sumOfWeightedInputs()
	{
		if(inputLayer)
		{
			return 0;
		}
		double sum = 0;
		for(int i = 0; i < inputs.size(); i++)
		{
			sum += inputs.get(i).activationValue * inputWeights.get(i);
		}
		return sum;
	}
	
	/*
	 * Calculates the delta value for the output layer perceptron given the expected output value.
	 */
	public void calculateDeltas(double[] expectedOutputs)
	{
		delta = -2 * (expectedOutputs[index] - activationValue)*(activationValue)*(1 - activationValue);
	}
	
	/*
	 * Calculates the delta value for a non-output layer perceptron
	 */
	public void calculateDeltas()
	{
		if(outputs == null)
		{
			throw new IllegalStateException("Output layer calling non-output layer function (calculateDeltas).");
		}
		
		double outputDeltaSum = 0;
		
		for(int i = 0; i < outputs.size(); i++)
		{
			Perceptron outputi = outputs.get(i);
			outputDeltaSum += outputi.delta * outputi.inputWeights.get(index);
		}
		delta = outputDeltaSum * activationValue * (1 - activationValue);
	}
	
	/*
	 * Update the weights the perceptron with the current delta values.
	 */
	public void updateWeights()
	{
		bias = bias - LEARNING_RATE * delta * activationValue;
		
		for(int i = 0; i < inputWeights.size(); i++)
		{
			double weight = inputWeights.get(i);
			inputWeights.set(i, weight - LEARNING_RATE * delta * activationValue);
		}
	}
	
	
	private static double logisticalFunction(double x)
	{
		return 1 / (1 + Math.pow(Math.E, -x));
	}
}
