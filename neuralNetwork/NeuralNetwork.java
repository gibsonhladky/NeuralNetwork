package neuralNetwork;

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
		
		final int numLayers = layerSizes.length;
		final int outputIndex = numLayers - 1;
		
		inputLayer = new InputLayer(layerSizes[0]);
		outputLayer = new OutputLayer(layerSizes[outputIndex], wg);
		networkLayers = new NetworkLayer[numLayers];
		
		networkLayers[0] = inputLayer;
		
		for(int i = 1; i < networkLayers.length - 1; i++)
		{
			networkLayers[i] = new HiddenLayer(layerSizes[i], wg);
			networkLayers[i].appendTo(networkLayers[i-1]);
		}

		networkLayers[outputIndex] = outputLayer;
		networkLayers[outputIndex].appendTo(networkLayers[outputIndex - 1]);
	}
	
	
	/*
	 * Trains the neural network on a single set of inputs and outputs
	 * with the neural network training algorithm.
	 */
	public void train(double[] inputs, double[] expectedOutputs)
	{
		setNetworkInputs(inputs);
		setExpectedNetworkOutputs(expectedOutputs);
		activateNetwork();
		
		while(! correctlyPredicts(expectedOutputs))
		{
			calculateNetworkErrors();
			adjustNetworkToErrors();
			activateNetwork();
		}
	}
	
	/*
	 * Returns the values output by the neural network given the
	 * inputs provided.
	 */
	public int predict(double[] inputs)
	{
		setNetworkInputs(inputs);
		activateNetwork();
		return indexOfMaxValue(networkOutputs());
	}
	
	public double[] networkOutputsFor(double[] inputs)
	{
		setNetworkInputs(inputs);
		activateNetwork();
		return networkOutputs();
	}
	
	private void setNetworkInputs(double[] inputs)
	{
		if(inputs == null || inputs.length != inputLayer.size())
		{
			throw new IllegalArgumentException("Illegal prediction inputs: " + inputs);
		}
		inputLayer.setInputs(inputs);
	}
	
	private void activateNetwork()
	{
		for(NetworkLayer l : networkLayers)
		{
			l.activate();
		}
	}
	
	private void setExpectedNetworkOutputs(double[] expectedOutputs)
	{
		if(expectedOutputs == null || expectedOutputs.length != outputLayer.size())
		{
			throw new IllegalArgumentException("Illegal training outputs: " + expectedOutputs);
		}
		outputLayer.setExpectedOutputs(expectedOutputs);
	}
	
	/*
	 * Enforces max value selection prediction
	 */
	private boolean correctlyPredicts(double[] expectedOutputs)
	{
		double[] actualOutputs = networkOutputs();
		return indexOfMaxValue(actualOutputs) == indexOfMaxValue(expectedOutputs);
	}
	
	private void calculateNetworkErrors()
	{
		for(int i = networkLayers.length - 1; i >= 0; i--)
		{
			networkLayers[i].calculateError();
		}
	}
	
	private void adjustNetworkToErrors()
	{
		for(NetworkLayer l : networkLayers)
		{
			l.adjustToError();
		}
	}
	
	private double[] networkOutputs()
	{
		return outputLayer.getOutputs();
	}
	
	private int indexOfMaxValue(double[] arr)
	{
		double max = arr[0];
		int index = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] > max)
			{
				index = i;
			}
		}
		return index;
	}
}
