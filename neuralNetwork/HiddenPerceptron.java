package neuralNetwork;
import java.util.ArrayList;
import java.util.Random;

public class HiddenPerceptron implements Perceptron
{	
	public static final double LEARNING_RATE = 0.1;
	public static final WeightGenerator weightGenerator = new RandomWeightGenerator();
	
	public int index; // current perceptron's index with it's layer
	public ArrayList<HiddenPerceptron> outputs; // next layer of perceptrons
	public ArrayList<HiddenPerceptron> inputs; // previous layer of perceptrons
	
	public boolean inputLayer;
	public double activationValue;
	public ArrayList<Double> inputWeights;
	public double bias;
	
	public double delta;
	
	
	public HiddenPerceptron(int index, ArrayList<HiddenPerceptron> inputs)
	{
		if(index < 0)
		{
			throw new IllegalArgumentException("Negative Perceptron index: " + index);
		}
		
		this.index = index;
		bias = weightGenerator.nextWeight();
		
		if(inputs != null)
		{
			this.inputs = inputs;
			int size = inputs.size();
			inputWeights = new ArrayList<Double>(size);
			for(int i = 0; i < size; i++)
			{
				double weight = weightGenerator.nextWeight();
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

	/*
	 * Calculates a new activation value based on inputs and weights.
	 * Does nothing for input layer perceptrons
	 */
	/* (non-Javadoc)
	 * @see neuralNetwork.Perceptron#activate()
	 */
	@Override
	public void activate()
	{
		if(inputLayer)
		{
			throw new IllegalStateException("Input layer perceptron called non-input activate method.");
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
	 * Update the weights the perceptron with the current delta values.
	 */
	/* (non-Javadoc)
	 * @see neuralNetwork.Perceptron#updateWeights()
	 */
	@Override
	public void adjustToError()
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

	@Override
	public double output() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double error() 
	{
		return 0;
	}
	
	public void addInputLink(Perceptron p)
	{
		
	}
	
	public void addOutputLink(Perceptron p)
	{
		
	}
}
