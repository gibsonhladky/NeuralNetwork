import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork 
{
	// a 2d ArrayList of Perceptrons, where 
	// the input layer is: layers.get(0), 
	// the output layer is: layers.get(layers.size()-1)
	// and the hidden layers are in between
	public ArrayList<ArrayList<Perceptron>> layers;	
	
	/*
	 * Initializes the neural network with no layers of perceptrons.
	 */
	public NeuralNetwork()
	{
		layers = new ArrayList<ArrayList<Perceptron>>();
	}
	
	/*
	 * Adds a layer of <size> perceptrons to the neural network.
	 * Links the outputs of the last layer to the inputs of the new layer.
	 */
	public void addLayer(int size)
	{
		// TODO - add a new layer of size Perceptrons to this neural network
		// make sure to set the inputs of the previous layer
		// and the outputs of the previous layer
		ArrayList<Perceptron> newLayer = new ArrayList<Perceptron>(size);

		ArrayList<Perceptron> inputs = null;
		if(layers.size() > 0)
		{
			inputs = layers.get(layers.size() - 1);
		}
		
		for(int i = 0; i < size; i++)
		{
			Perceptron newPerceptron = new Perceptron(i, inputs);
			newLayer.add(newPerceptron);
		}
		
		if(layers.size() > 1)
		{
			ArrayList<Perceptron> previousLayer = layers.get(layers.size() - 1);
			setOutputs(previousLayer, newLayer);
		}
		
		layers.add(newLayer);
		
	}
	
	private void setOutputs(ArrayList<Perceptron> inner, ArrayList<Perceptron> outer)
	{
		for(Perceptron p : inner)
		{
			p.outputs = outer;
		}
	}
	
	/*
	 * Trains the neural network on a single set of inputs and outputs
	 * with the neural network training algorithm.
	 */
	public void train(double[] inputs, double[] outputs)
	{
		for(int i = 0; i < 5; i++)
		{
			activateInputs(inputs);
			propagateInputsToAnswers();
			calculateOutputDeltas(outputs);
			propagateDeltasBack();
			updateWeightsWithDeltas();
		}
	}
	
	/*
	 * 
	 */
	private void activateInputs(double[] inputs)
	{
		if(layers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		if(inputs.length != layers.get(0).size())
		{
			throw new IllegalArgumentException("Input list is of different size than input layer.");
		}
		
		ArrayList<Perceptron> inputLayer = layers.get(0);
		
		for(int i = 0; i < inputLayer.size(); i++)
		{
			inputLayer.get(i).activationValue = inputs[i];
		}
		
	}
	
	private void propagateInputsToAnswers()
	{
		if(layers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		/*
		 * Activate layers from input to output
		 */
		for(ArrayList<Perceptron> layer : layers)
		{
			for(Perceptron p : layer)
			{
				p.activate();
			}
		}
	}
	
	private void calculateOutputDeltas(double[] outputs)
	{
		if(layers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		ArrayList<Perceptron> outputLayer = layers.get(layers.size() - 1);
		
		if(outputLayer.size() != outputs.length)
		{
			throw new IllegalArgumentException("Output list has different size than output layer.");
		}
		
		for(Perceptron p : outputLayer)
		{
			p.calculateDeltas(outputs);
		}
		
	}
	
	private void propagateDeltasBack()
	{
		if(layers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		for(int i = layers.size() - 2; i > 0; i--)
		{
			ArrayList<Perceptron> currentLayer = layers.get(i);
			for(Perceptron p : currentLayer)
			{
				p.calculateDeltas();
			}
		}
	}
	
	private void updateWeightsWithDeltas()
	{
		for(ArrayList<Perceptron> layer : layers)
		{
			for(Perceptron p : layer)
			{
				p.updateWeights();
			}
		}
	}
	
	private double[] extractOutputs()
	{
		if(layers.size() == 0)
		{
			throw new IllegalStateException("Neural Network has no layers.");
		}
		
		ArrayList<Perceptron> outputLayer = layers.get(layers.size() - 1);
		double[] outputs = new double[outputLayer.size()];
		
		for(int i = 0; i < outputLayer.size(); i++)
		{
			outputs[i] = outputLayer.get(i).activationValue;
		}
		
		return outputs;
	}
	
	public double[] predict(double[] inputs)
	{
		// TODO - return the activations of the last layer's Perceptrons
		// as an array of doubles (the highest of which is our best prediction)
		
		activateInputs(inputs);
		propagateInputsToAnswers();
		
		return extractOutputs();
	}			
}
