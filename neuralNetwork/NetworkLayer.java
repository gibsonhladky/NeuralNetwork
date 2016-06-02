package neuralNetwork;

import java.util.ArrayList;

/*
 * Describes the most basic functions a network layer
 * will be responsible for: propagating inputs,
 * back-propagating errors, and adjusting to those errors.
 * Abstract implementations are described in the most general
 * form. Any layer dealing with I/O will have to override
 * at least one method.
 */
abstract class NetworkLayer {

	ArrayList<Perceptron> perceptrons;
	
	/*
	 * Creates a network layer with size perceptrons, all connected
	 * to the previous layer.
	 */
	protected NetworkLayer(int size, ArrayList<Perceptron> previousLayer)
	{
		perceptrons = new ArrayList<Perceptron>(size);
		for(int i = 0; i < size; i++)
		{
			perceptrons.add(new Perceptron(i, previousLayer));
		}
	}
	
	/*
	 * Returns the number of perceptrons
	 * in the network layer.
	 */
	protected int size()
	{
		return perceptrons.size();
	}
	
	/*
	 * Calculates and sets the output value of each perceptron
	 * in this layer, preparing the next layer to call
	 * this method.
	 */
	protected void feedForwardActivation()
	{
		for(Perceptron p : perceptrons)
		{
			p.activate();
		}
	}
	
	/*
	 * Calculates and sets the error of each perceptron
	 * in this layer, preparing the previous layer to 
	 * call this method.
	 */
	protected void backPropagateError()
	{
		for(Perceptron p : perceptrons)
		{
			p.calculateDeltas();
		}
	}
	
	/*
	 * Adjusts the weights of each perceptron in this layer
	 * based on the error calculated.
	 */
	protected void adjustToError()
	{
		for(Perceptron p : perceptrons)
		{
			p.updateWeights();
		}
	}
	
}
