package neuralNetwork;

import java.util.ArrayList;

abstract class NetworkLayer {

	ArrayList<Perceptron> perceptrons;
	
	protected int size()
	{
		return perceptrons.size();
	}
	
	protected void feedForwardActivation()
	{
		
	}
	
	protected void backPropagateError()
	{
		
	}
	
	protected void adjustToError()
	{
		
	}
	
}
