import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.InputLayer;

public class InputLayerTest {
	
	private final double ONE = 1;
	
	private InputLayer testLayer;
	
	@Before
	public void setup()
	{
		testLayer = new InputLayer(0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesSmallSize()
	{
		testLayer = new InputLayer(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooSmall()
	{
		double[] testInputs = {ONE};
		testLayer.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooLarge()
	{
		double[] testInputs = {ONE, ONE, ONE};
		testLayer.setInputs(testInputs);
	}
	
	@Test
	public void feedForwardActivationCalculation()
	{
		double[] testInputs = {ONE, ONE};
		testLayer.setInputs(testInputs);
		
	}

}
