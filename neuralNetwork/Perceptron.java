package neuralNetwork;

public interface Perceptron {

	/*
	 * Calculates a new activation value based on inputs and weights.
	 * Does nothing for input layer perceptrons
	 */
	public void activate();

	/*
	 * Update the weights of the perceptron 
	 * based on the error down the network.
	 */
	public void adjustToError();
	
	/*
	 * Returns the activation value of the perceptron. This is used to
	 * pass the value down the network.
	 */
	public double output();
	
	/*
	 * Logistical activation function shared amongst all
	 * perceptrons.
	 */
	static double activationFunction(double input)
	{
		return 1 / (1 + Math.pow(Math.E, -input));
	}

}