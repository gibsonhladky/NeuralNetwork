package neuralNetwork;

public interface Perceptron {

	/*
	 * Calculates a new activation value based on inputs and weights.
	 * Does nothing for input layer perceptrons
	 */
	public void activate();

	/*
	 * Calculates the delta value for a non-output layer perceptron
	 */
	public void calculateDeltas();

	/*
	 * Update the weights the perceptron with the current delta values.
	 */
	public void updateWeights();
	
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