package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class InputLayer implements NetworkLayer{

	private InputPerceptron[] perceptrons;
	
	/*
	 * Reference to the next layer in the network
	 * to be wired to the perceptrons in the input layer.
	 */
	private List<NetworkLayer> nextLayers;
	
	/*
	 * Inputs to neural networks are handled at the input layer 
	 * level to clarify how the network should handle inputs.
	 */
	private double[] inputs;
	
	/*
	 * Creates a layer with no previous layer.
	 */
	public InputLayer(int size) 
	{
		if(size < 1)
		{
			throw new IllegalArgumentException("Cannot create an input layer of zero or negative size.");
		}
		perceptrons = new InputPerceptron[size];
		for(int i = 0; i < perceptrons.length; i++)
		{
			perceptrons[i] = new InputPerceptron();
		}
		inputs = new double[size];
		nextLayers = new ArrayList<NetworkLayer>();
	}
	
	/*
	 * Activates the perceptrons in the input layer
	 * according to the inputs currently specified.
	 */
	public void activate()
	{
		for(InputPerceptron p : perceptrons)
		{
			p.activate();
		}
	}
	
	/*
	 * The input layer does not have to handle errors.
	 */
	public void calculateError(){}
	
	/*
	 * The input layer does not have to handle errors.
	 */
	public void adjustToError(){}
	
	
	public Perceptron[] perceptrons()
	{
		return perceptrons;
	}
	
	/*
	 * Input layers have a unique function to take inputs
	 * from outside the network and insert them in perceptrons.
	 */
	public void setInputs(double[] newInputs)
	{
		if(newInputs.length != inputs.length)
		{
			throw new IllegalArgumentException("Cannot set inputs from a list of different size.");
		}
		inputs = newInputs;
	}
	
	/*
	 * Rewires this input layer to a new layer of
	 * the network. All connections to the previous
	 * layer are lost.
	 */
	public void setNextLayer(NetworkLayer layer)
	{
		nextLayers.add(layer);
	}
	
	/*
	 * Returns the layer that this input layer is
	 * currently wired to.
	 */
	public List<NetworkLayer> getNextLayer()
	{
		return nextLayers;
	}
	
}
