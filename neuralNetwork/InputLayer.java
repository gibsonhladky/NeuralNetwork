package neuralNetwork;

import java.util.ArrayList;

public class InputLayer implements NetworkLayer{

	ArrayList<InputPerceptron> perceptrons;
	
	/*
	 * Inputs to neural networks are handled at the input layer 
	 * level to clarify how the network should handle inputs.
	 */
	double[] inputs;
	
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
		for(Perceptron p : perceptrons)
		{
			p.activate(inputs);
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
			throw new IllegalArgumentException("Must specify imputs of the correct size.");
		}
		inputs = newInputs;
	}
	
	

}
