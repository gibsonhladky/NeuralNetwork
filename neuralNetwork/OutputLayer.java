package neuralNetwork;

import java.util.ArrayList;

public class OutputLayer implements NetworkLayer {

	/*
	 * Final layer of perceptrons in a neural network.
	 * These perceptrons will specifically handle unique
	 * calculations and feeding the output to the neural network.
	 */
	ArrayList<OutputPerceptron> perceptrons;
	
	@Override
	public void feedForwardActivation() {
		
	}

	@Override
	public void backPropagateError() {
		
	}

	@Override
	public void adjustToError() {
		
	}
	
	/*
	 * Returns the output of each perceptron in this layer.
	 */
	public double[] getOutputs()
	{
		
		return null;
	}

}
