package neuralNetwork;

public interface Perceptron {

	/*
	 * Calculates a new activation value based on inputs and weights.
	 * Does nothing for input layer perceptrons
	 */
	void activate();

	/*
	 * Calculates the delta value for a non-output layer perceptron
	 */
	void calculateDeltas();

	/*
	 * Update the weights the perceptron with the current delta values.
	 */
	void updateWeights();

}