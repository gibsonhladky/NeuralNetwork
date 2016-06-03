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
		addBias();
	}
	
	public PerceptronInputLink(WeightGenerator wg, ArrayList<Perceptron> inputs)
	{
		weightGen = wg;
		addBias();
		addAll(inputs);
	}
	
	private void addAll(List<Perceptron> inputs)
	{
		inputWeightPairs.addAll(inputs);
	}
	
	private void addBias()
	{
		inputWeightPairs.add(new Pair(new BiasPerceptron(), 1));
	}
	
}
