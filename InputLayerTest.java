import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.InputLayer;

public class InputLayerTest {
	
	final double ONE = 1;

	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooSmall()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {ONE};
		testLayer.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooLarge()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {ONE, ONE, ONE};
		testLayer.setInputs(testInputs);
	}
	
	@Test
	public void feedForwardActivationCalculation()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {ONE, ONE};
		testLayer.setInputs(testInputs);
		
	}

}
