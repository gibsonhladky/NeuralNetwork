package neuralNetwork;

/*
 * Describes the most basic functions a network layer
 * will be responsible for: propagating inputs,
 * back-propagating errors, and adjusting to those errors.
 */
public interface NetworkLayer {
	
	/*
	 * Calculates and sets the output value of each perceptron
	 * in this layer, preparing the next layer to call
	 * this method.
	 */
	public void activate();
	
	/*
	 * Calculates and sets the error of each perceptron
	 * in this layer, preparing the previous layer to 
	 * call this method.
	 */
	public void calculateError();
	
	/*
	 * Adjusts the perceptron inputs to take a small
	 * step towards a better value.
	 */
	public void adjustToError();
	
	/*
	 * Appends this network layer to another layer.
	 * The outputs of the other layer are wired to this
	 * network, and this network's inputs are wired to
	 * the previous layer. A layer can be appended to
	 * more than one layer.
	 */
	public void appendTo(NetworkLayer l);
	
	/*
	 * 
	 */
	public Perceptron[] perceptrons();
	
}
