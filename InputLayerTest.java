import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.InputLayer;

public class InputLayerTest {
	
	final double DUMBYINPUT = 1.0;

	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooSmall()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {DUMBYINPUT};
		testLayer.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooLarge()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {DUMBYINPUT, DUMBYINPUT, DUMBYINPUT};
		testLayer.setInputs(testInputs);
	}
	
	@Test
	public void feedForwardActivationCalculation()
	{
		InputLayer testLayer = new InputLayer(2);
		double[] testInputs = {DUMBYINPUT, DUMBYINPUT};
		testLayer.setInputs(testInputs);
	}

}
