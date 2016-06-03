package neuralNetwork;

/*
 * Describes the most basic functions a network layer
 * will be responsible for: propagating inputs,
 * back-propagating errors, and adjusting to those errors.
 * Abstract implementations are described in the most general
 * form. Any layer dealing with I/O will have to override
 * at least one method.
 */
interface NetworkLayer {
	
	/*
	 * Calculates and sets the output value of each perceptron
	 * in this layer, preparing the next layer to call
	 * this method.
	 */
	public void feedForwardActivation();
	
	/*
	 * Calculates and sets the error of each perceptron
	 * in this layer, preparing the previous layer to 
	 * call this method.
	 */
	public void backPropagateError();
	
	/*
	 * Adjusts the weights of each perceptron in this layer
	 * based on the error calculated.
	 */
	public void adjustToError();
	
}
