package neuralNetwork;

import java.util.ArrayList;

public class InputLayer implements NetworkLayer{

	private ArrayList<InputPerceptron> perceptrons;
	
	/*
	 * Reference to the next layer in the network
	 * to be wired to the perceptrons in the input layer.
	 */
	private NetworkLayer nextLayer;
	
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
		perceptrons = new ArrayList<InputPerceptron>(size);
		for(int i = 0; i < perceptrons.size(); i++)
		{
			perceptrons.add(new InputPerceptron(i));
		}
		inputs = new double[size];
	}
	
	/*
	 * Activates the perceptrons in the input layer
	 * according to the inputs currently specified.
	 */
	public void feedForwardActivation()
	{
		for(InputPerceptron p : perceptrons)
		{
			p.activate();
		}
	}
	
	/*
	 * The input layer does not have to handle errors.
	 */
	public void backPropagateError(){}
	
	/*
	 * The input layer does not have to handle errors.
	 */
	public void adjustToError(){}
	
	/*
	 * Input layers have a unique function to take inputs
	 * from outside the network and insert them in perceptrons.
	 */
	public void setInputs(double[] newInputs)
	{
		if(newInputs.length != inputs.length)
		{
			throw new IllegalArgumentException("Must specify imputs of the correct size (" + inputs.length + ".");
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
		nextLayer = layer;
	}
	
	/*
	 * Returns the layer that this input layer is
	 * currently wired to.
	 */
	public NetworkLayer getNextLayer()
	{
		return nextLayer;
	}
	
}
