package neuralNetwork;
import java.util.ArrayList;

public class NeuralNetwork 
{
	// a 2d ArrayList of Perceptrons, where 
	// the input layer is: layers.get(0), 
	// the output layer is: layers.get(layers.size()-1)
	// and the hidden layers are in between
	
	private InputLayer inputLayer;
	private OutputLayer outputLayer;
	private NetworkLayer[] networkLayers;
	
	/*
	 * Initializes the neural network with no layers of perceptrons
	 * and a threshold.
	 */
	public NeuralNetwork(int[] layerSizes, WeightGenerator wg)
	{
		if(layerSizes == null || layerSizes.length < 2)
		{
			throw new IllegalArgumentException("Illegal layerSizes in NeuralNetwork constructor: " + layerSizes);
		}
		if(wg == null)
		{
			throw new IllegalArgumentException("Null weight generator in NeuralNetwork constructor.");
		}
		inputLayer = new InputLayer(layerSizes[0]);
		outputLayer = new OutputLayer(layerSizes[layerSizes.length - 1], wg);
	}
	
	
	/*
	 * Trains the neural network on a single set of inputs and outputs
	 * with the neural network training algorithm.
	 */
	public void train(double[] inputs, double[] outputs)
	{
		if(inputs == null || inputs.length != inputLayer.size())
		{
			throw new IllegalArgumentException("Illegal training inputs: " + inputs);
		}
		if(outputs == null)
		{
			throw new IllegalArgumentException("Illegal training outputs: " + outputs);
		}
	}
	
	/*
	 * Returns the values output by the neural network given the
	 * inputs provided.
	 */
	public double[] predict(double[] inputs)
	{
		return null;
	}			
}
