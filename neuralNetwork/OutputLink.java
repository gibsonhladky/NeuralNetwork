package neuralNetwork;

import java.util.ArrayList;

/*
 * OutputLink represents the outgoing edges from
 * perceptrons. It is used to keep track of each link
 * between perceptrons and to produce the error
 * associated with the perceptron from it's outputs.
 */
public class OutputLink {

	private ArrayList<Perceptron> outputs;
	private ArrayList<Double> weights;
	private int size;
	
	public OutputLink()
	{
		outputs = new ArrayList<Perceptron>();
		weights = new ArrayList<Double>();
		size = 0;
	}
	
	/*
	 * Returns the total error associated with these links.
	 */
	public double getAssociatedError()
	{
		double error = 0;
		for(int i = 0; i < size; i++)
		{
			error += outputs.get(i).error() * weights.get(i);
		}
		return error;
	}
	
	/*
	 * Introduces a new link to the OutputLink.
	 */
	public void addLink(Perceptron newOutput, Double weight)
	{
		outputs.add(newOutput);
		weights.add(weight);
		size++;
	}
	
}
