package neuralNetwork;
import java.util.ArrayList;

public class NeuralNetwork 
{
	// a 2d ArrayList of Perceptrons, where 
	// the input layer is: layers.get(0), 
	// the output layer is: layers.get(layers.size()-1)
	// and the hidden layers are in between
	
	private ArrayList<Perceptron> inputLayer;
	public ArrayList<ArrayList<Perceptron>> hiddenLayers;
	private ArrayList<Perceptron> outputLayer;
	
	/*
	 * Initializes the neural network with no layers of perceptrons
	 * and a threshold.
	 */
	public NeuralNetwork()
	{
		inputLayer = new ArrayList<Perceptron>();
		hiddenLayers = new ArrayList<ArrayList<Perceptron>>();
		outputLayer = new ArrayList<Perceptron>();
	}
	
	public void setInputSize(int size)
	{
		for(int i = 0; i < size; i++)
		{
			inputLayer.add(new Perceptron(i, null));
		}
	}
	
	public void setOutputSize(int size)
	{
		for(int i = 0; i < size; i++)
		{
			outputLayer.add(new Perceptron(i, hiddenLayers.get(hiddenLayers.size() - 1)));
		}
	}
	
	/*
	 * Adds a layer of <size> perceptrons to the neural network.
	 * Links the outputs of the last layer to the inputs of the new layer.
	 */
	public void addLayer(int size)
	{
		if(size < 1)
		{
			throw new IllegalArgumentException("Cannot add a layer with size smaller than 1.");
		}
		ArrayList<Perceptron> newLayer = new ArrayList<Perceptron>(size);
		
		// Determine the input layer to the new layer
		ArrayList<Perceptron> oldOutputLayer = null;
		if(hiddenLayers.size() > 0)
		{
			oldOutputLayer = hiddenLayers.get(hiddenLayers.size() - 1);
		}
		
		// Populate the layer with perceptrons
		for(int i = 0; i < size; i++)
		{
			Perceptron newPerceptron = new Perceptron(i, oldOutputLayer);
			newLayer.add(newPerceptron);
		}
		
		hiddenLayers.add(newLayer);
		
		if(hiddenLayers.size() > 1)
		{
			setOutputs(oldOutputLayer, newLayer);
		}
	}
	
	/*
	 * Sets the outputs of the first layer to the second layer.
	 */
	private void setOutputs(ArrayList<Perceptron> oldOutputLayer, ArrayList<Perceptron> newOutputLayer)
	{
		for(Perceptron p : oldOutputLayer)
		{
			p.outputs = newOutputLayer;
		}
	}
	
	/*
	 * Trains the neural network on a single set of inputs and outputs
	 * with the neural network training algorithm.
	 */
	public void train(double[] inputs, double[] outputs)
	{
		activateInputs(inputs);
		propagateInputsToAnswers();
		
		while(!correctlyIdentifies(outputs))
		{
			calculateOutputDeltas(outputs);
			propagateDeltasBack();
			updateWeightsWithDeltas();
			activateInputs(inputs);
			propagateInputsToAnswers();
		}
	}
	
	/*
	 * Returns true iff the neural network correctly identifies the
	 * training sample
	 */
	private boolean correctlyIdentifies(double[] expectedOutputs)
	{
		double[] actualOutputs = extractOutputs();
		int expectedIndex = getMaxIndex(expectedOutputs);
		int actualIndex = getMaxIndex(actualOutputs);
		
		return expectedIndex == actualIndex;
	}
	
	/*
	 * Returns the index of the largest element in an array
	 */
	private int getMaxIndex(double[] list)
	{
		int max = 0;
		for(int i = 0; i < list.length; i++)
		{
			if(list[i] > list[max])
			{
				max = i;
			}
		}
		return max;
	}
	
	/*
	 * Feeds the given inputs into the first layer of the neural network.
	 */
	private void activateInputs(double[] inputs)
	{
		if(hiddenLayers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		ArrayList<Perceptron> inputLayer = hiddenLayers.get(0);
		
		if(inputs.length != inputLayer.size())
		{
			throw new IllegalArgumentException("Input list is of different size than input layer.");
		}
		
		for(Perceptron p : inputLayer)
		{
			p.activate(inputs);
		}
		
	}
	
	/*
	 * Pushes activation values from input layer to output layer of neural network.
	 * MUST be called after activateInputs.
	 */
	private void propagateInputsToAnswers()
	{
		if(hiddenLayers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		// Activate all layers after input, in order, to push results through the network
		for(int i = 1; i < hiddenLayers.size(); i++)
		{
			for(Perceptron p : hiddenLayers.get(i))
			{
				p.activate();
			}
		}
	}
	
	/*
	 * Calculates the difference between actual and expected outputs of the 
	 * neural network.
	 */
	private void calculateOutputDeltas(double[] expectedOutputs)
	{
		if(hiddenLayers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		ArrayList<Perceptron> outputLayer = hiddenLayers.get(hiddenLayers.size() - 1);
		
		if(outputLayer.size() != expectedOutputs.length)
		{
			throw new IllegalArgumentException("Output list has different size than output layer.");
		}
		
		for(Perceptron p : outputLayer)
		{
			p.calculateDeltas(expectedOutputs);
		}
		
	}
	
	/*
	 * Calculates the delta's of each layer based on the output delta's
	 * of each perceptron.
	 */
	private void propagateDeltasBack()
	{
		if(hiddenLayers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		// Traverse the network backwards after the output layer
		// to pull delta values through the network.
		for(int i = hiddenLayers.size() - 2; i >= 0; i--)
		{
			ArrayList<Perceptron> currentLayer = hiddenLayers.get(i);
			for(Perceptron p : currentLayer)
			{
				p.calculateDeltas();
			}
		}
	}
	
	/*
	 * Recalculates all weights in the neural network based on the 
	 * delta values of each perceptron
	 */
	private void updateWeightsWithDeltas()
	{
		for(ArrayList<Perceptron> layer : hiddenLayers)
		{
			for(Perceptron p : layer)
			{
				p.updateWeights();
			}
		}
	}
	
	/*
	 * Returns the output values generated by the neural network
	 */
	private double[] extractOutputs()
	{
		if(hiddenLayers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		ArrayList<Perceptron> outputLayer = hiddenLayers.get(hiddenLayers.size() - 1);
		double[] outputs = new double[outputLayer.size()];
		
		for(int i = 0; i < outputLayer.size(); i++)
		{
			outputs[i] = outputLayer.get(i).activationValue;
		}
		
		return outputs;
	}
	
	/*
	 * Returns the values output by the neural network given the
	 * inputs provided.
	 */
	public double[] predict(double[] inputs)
	{
		activateInputs(inputs);
		propagateInputsToAnswers();
		
		return extractOutputs();
	}			
}
