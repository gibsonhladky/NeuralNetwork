import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.InputLayer;

public class InputLayerTest {

	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooSmall()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {1.0};
		testLayer.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooLarge()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {1.0, 1.0, 1.0};
		testLayer.setInputs(testInputs);
	}
	
	@Test
	public void feedForwardActivationCalculation()
	{
		
	}

}
