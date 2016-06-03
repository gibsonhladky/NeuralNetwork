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
public class PerceptronInputLink {
	
	private final WeightGenerator weightGen;

	private ArrayList< Pair<Perceptron, Double> > inputWeightPairs;
	
	public PerceptronInputLink()
	{
		this(new RandomWeightGenerator());
	}
	
	public PerceptronInputLink(WeightGenerator wg)
	{
		weightGen = wg;
		inputWeightPairs = new ArrayList< Pair<Perceptron, Double>>(1);
		addBias();
	}
	
	public PerceptronInputLink(WeightGenerator wg, ArrayList<Perceptron> inputs)
	{
		weightGen = wg;
		inputWeightPairs = new ArrayList< Pair<Perceptron, Double>>(inputs.size() + 1);
		addBias();
		addAll(inputs);
	}
	
	public double[] inputValues()
	{
		double[] inputValues = new double[inputWeightPairs.size()];
		
		for(int i = 0; i < inputWeightPairs.size(); i++)
		{
			Pair<Perceptron, Double> pair = inputWeightPairs.get(i);
			Perceptron p = pair.getLeft();
			double weight = pair.getRight();
			inputValues[i] = p.output() * weight;
		}
		
		return inputValues;
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
