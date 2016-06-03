package neuralNetwork;

import java.util.ArrayList;

/*
 * Perceptron input links store the (output, weight)
 * pairs for connections between perceptrons.
 * The link provides weighted input values for
 * the perceptron, and handles the management of 
 * which perceptrons are connected to it.
 */
public class PerceptronInputLink {

	private ArrayList<Perceptron> inputPerceptrons;
	private ArrayList<Double> inputWeights;
	
	public PerceptronInputLink()
	{
		inputPerceptrons = new ArrayList<Perceptron>();
		inputPerceptrons.add(new BiasPerceptron());
	}
	
}
