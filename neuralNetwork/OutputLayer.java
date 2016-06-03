package neuralNetwork;

import java.util.ArrayList;

public class OutputLayer implements NetworkLayer {

	/*
	 * Final layer of perceptrons in a neural network.
	 * These perceptrons will specifically handle unique
	 * calculations and feeding the output to the neural network.
	 */
	ArrayList<OutputPerceptron> perceptrons;
	
	/*
	 * Stores the expected output values for 
	 */
	double[] expectedOutputs;
	
	@Override
	public void feedForwardActivation() {
		for(Perceptron p : perceptrons)
		{
			p.activate();
		}
	}

	@Override
	public void backPropagateError() {
		for(HiddenPerceptron p : perceptrons)
		{
			p.calculateDeltas(expectedOutputs);
		}
	}

	@Override
	public void adjustToError() {
		for(Perceptron p : perceptrons)
		{
			p.updateWeights();
		}
	}
	
	public void setExpectedOutputs(double[] outputs)
	{
		expectedOutputs = outputs;
	}
	
	/*
	 * Returns the output of each perceptron in this layer.
	 */
	public double[] getOutputs()
	{
		return null;
	}

}
