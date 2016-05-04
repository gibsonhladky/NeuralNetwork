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
		
		if(inputs != null)
		{
			this.inputs = inputs;
			int size = inputs.size();
			inputWeights = new ArrayList<Double>(size);
			for(int i = 0; i < size; i++)
			{
				inputWeights.add(randGen.nextDouble());
			}
		}
		else
		{
			inputLayer = true;
		}
		this.outputs = null;
	}

	public void activate()
	{
		// TODO - calculate a new activation value
		// for perceptrons in input layer, do nothing
		if(inputLayer)
		{
			return;
		}
		
		activationValue = logisticalFunction(bias + sumOfWeightedInputs());
	}
	
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
	
	public void calculateDeltas(double[] expectedOutputs)
	{
		// TODO - calculate deltas for perceptrons in the output layer
		delta = expectedOutputs[index] - activationValue;
	}
	
	public void calculateDeltas()
	{
		// TODO - calculate deltas for non-output layer perceptrons
		
		if(outputs == null)
		{
			throw new IllegalStateException("Output layer calling non-output layer function.");
		}
		
		double outputDeltaSum = 0;
		for(int i = 0; i < outputs.size(); i++)
		{
			Perceptron outputi = outputs.get(i);
			outputDeltaSum += outputi.delta * outputi.inputWeights.get(index);
		}
		delta = outputDeltaSum * LEARNING_RATE * activationValue * (1 - activationValue);
	}
	
	public void updateWeights()
	{
		// TODO - use deltas to update weights 
		// for all non-input layer perceptrons
		if(inputLayer)
		{
			bias = bias - LEARNING_RATE * delta * activationValue;
			return;
		}
		
		for(int i = 0; i < inputWeights.size(); i++)
		{
			double weight = inputWeights.get(i);
			weight = weight - LEARNING_RATE * delta * activationValue;
		}
	}
	
	private static double logisticalFunction(double x)
	{
		return 1 / (1 + Math.pow(Math.E, -x));
	}
}
