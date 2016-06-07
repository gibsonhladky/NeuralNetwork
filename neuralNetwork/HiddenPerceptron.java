package neuralNetwork;
import java.util.ArrayList;
import java.util.Random;

public class HiddenPerceptron implements Perceptron
{	
	public static final double LEARNING_RATE = 0.1;
	
	public int index; // current perceptron's index with it's layer
	public ArrayList<HiddenPerceptron> outputs; // next layer of perceptrons
	public ArrayList<HiddenPerceptron> inputs; // previous layer of perceptrons
	
	public boolean inputLayer;
	public double output;
	public ArrayList<Double> inputWeights;
	public double bias;
	
	public double error;
	
	private InputLinks inputLinks;
	private OutputLink outputLinks;
	
	
	public HiddenPerceptron(WeightGenerator wg)
	{
		if(index < 0)
		{
			throw new IllegalArgumentException("Negative Perceptron index: " + index);
		}
		bias = wg.nextWeight();
		
		if(inputs != null)
		{
			int size = inputs.size();
			inputWeights = new ArrayList<Double>(size);
			for(int i = 0; i < size; i++)
			{
				double weight = wg.nextWeight();
				inputWeights.add(weight);
			}
		}
		else
		{
			inputLayer = true;
			inputWeights = new ArrayList<Double>();
		}
		this.outputs = null;
		
		
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
		
		bias = bias - LEARNING_RATE * error * output;
		
		for(int i = 0; i < inputWeights.size(); i++)
		{
			double weight = inputWeights.get(i);
			inputWeights.set(i, weight - LEARNING_RATE * error * output);
		}
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
