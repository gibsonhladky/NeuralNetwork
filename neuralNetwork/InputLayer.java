package neuralNetwork;

import java.util.ArrayList;

public class InputLayer implements NetworkLayer{

	/*
	 * Inputs to neural networks are handled at the input layer 
	 * level to clarify how the network should handle inputs.
	 */
	double[] inputs;
	
	/*
	 * Creates a layer with no previous layer.
	 */
	protected InputLayer(int size) 
	{
		perceptrons = new ArrayList<Perceptron>(size);
		for(int i = 0; i < perceptrons.size(); i++)
		{
			perceptrons.add(new InputPerceptron(i));
		}
		inputs = new double[size];
	}
	
	@Override
	protected void feedForwardActivation()
	{
		for(Perceptron p : perceptrons)
		{
			p.activate(inputs);
		}
	}
	
	/*
	 * The input layer does not have to handle errors.
	 */
	@Override
	protected void backPropagateError()
	{
		
	}
	
	/*
	 * The input layer does not have to handle errors.
	 */
	@Override
	protected void adjustToError()
	{
		
	}
	
	/*
	 * Input layers have a unique function to take inputs
	 * from outside the network and insert them in perceptrons.
	 */
	protected void setInputs(double[] newInputs)
	{
		if(newInputs.length != inputs.length)
		{
			throw new IllegalArgumentException("Must specify imputs of the correct size.");
		}
		inputs = newInputs;
	}
	
	

}
