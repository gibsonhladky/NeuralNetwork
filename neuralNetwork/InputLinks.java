package neuralNetwork;

import utilities.Pair;
import java.util.ArrayList;
import java.util.List;

/*
 * Perceptron input links store the (output, weight)
 * pairs for connections between perceptrons.
 * The link provides weighted input values for
 * the perceptron, and handles the management of 
 * which perceptrons are connected to it.
 */
public class InputLinks {
	
	private final double LEARNING_RATE = 0.1;
	
	private final WeightGenerator weightGen;
	private ArrayList< Pair<Perceptron, Double> > inputWeightPairs;
	
	public InputLinks()
	{
		this(new RandomWeightGenerator());
	}
	
	public InputLinks(WeightGenerator wg)
	{
		weightGen = wg;
		inputWeightPairs = new ArrayList< Pair<Perceptron, Double>>(1);
		addBias();
	}
	
	public InputLinks(WeightGenerator wg, ArrayList<Perceptron> inputs)
	{
		weightGen = wg;
		inputWeightPairs = new ArrayList< Pair<Perceptron, Double>>(inputs.size() + 1);
		addBias();
		addAll(inputs);
	}
	
	public double inputValue()
	{
		double inputValue = 0;
		
		for(int i = 0; i < inputWeightPairs.size(); i++)
		{
			Pair<Perceptron, Double> pair = inputWeightPairs.get(i);
			Perceptron p = pair.getLeft();
			double weight = pair.getRight();
			inputValue += p.output() * weight;
		}
		
		return inputValue;
	}
	
	public void adjustToErrorGivenOutput(double error, double output)
	{
		for(Pair<Perceptron, Double> pair : inputWeightPairs)
		{
			double weight = pair.getRight();
			pair.setRight(weight + LEARNING_RATE * error * output);
		}
	}
	
	public void addLinkFrom(Perceptron p)
	{
		inputWeightPairs.add(new Pair<Perceptron, Double>(p, weightGen.nextWeight()));
	}
	
	private void addAll(List<Perceptron> inputs)
	{
		for(Perceptron p : inputs)
		{
			inputWeightPairs.add(new Pair<Perceptron, Double>(p, weightGen.nextWeight()));
		}
	}
	
	private void addBias()
	{
		Double nextWeight = weightGen.nextWeight();
		Pair<Perceptron, Double> bias = new Pair<Perceptron, Double>(new BiasPerceptron(), nextWeight);
		inputWeightPairs.add(bias);
	}
	
}
