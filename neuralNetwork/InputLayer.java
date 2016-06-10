package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class InputLayer implements NetworkLayer{

	private InputPerceptron[] perceptrons;
	
	/*
	 * Reference to the next layers in the network
	 * to be wired to the perceptrons in the input layer.
	 */
	private List<NetworkLayer> nextLayers;
	
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
		if(newInputs.length != perceptrons.length)
		{
			throw new IllegalArgumentException("Cannot set inputs from a list of different size.");
		}
		for(int i = 0; i < perceptrons.length; i++)
		{
			perceptrons[i].setInput(newInputs[i]);
		}
	}
	
	/*
	 * Rewires this input layer to a new layer of
	 * the network. All connections to the previous
	 * layer are lost.
	 */
	public void addNextLayer(NetworkLayer newLayer)
	{
		if(newLayer == null)
		{
			throw new IllegalArgumentException("Cannot add a null layer to an input layer.");
		}
		nextLayers.add(newLayer);
	}
	
	/*
	 * Returns the layer that this input layer is
	 * currently wired to.
	 */
	public List<NetworkLayer> nextLayers()
	{
		return nextLayers;
	}

	/*
	 * Input layers only care about provided inputs, not other network layers.
	 */
	@Override
	public void appendTo(NetworkLayer l) {}
	
	public int size()
	{
		return perceptrons.length;
	}
	
}
