package neuralNetwork;

public class InputLayer extends NetworkLayer{

	double[] inputs;
	
	/*
	 * Creates a layer with no previous layer.
	 */
	protected InputLayer(int size) 
	{
		super(size, null);
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
	
	protected void setInputs(double[] newInputs)
	{
		if(newInputs.length != inputs.length)
		{
			throw new IllegalArgumentException("Must specify imputs of the correct size.");
		}
		inputs = newInputs;
	}
	
	

}
