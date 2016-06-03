import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.PerceptronInputLink;

public class PerceptronInputLinkTest {

	@Test
	public void constructorNoArgsCreatesOnlyRandomBias()
	{
		PerceptronInputLink testLink = new PerceptronInputLink();
		double[] actualInputs = testLink.inputValues();
		
		int expectedLength = 1;
		assertEquals(expectedLength, actualInputs.length);
		double biasValue = actualInputs[0];
		assertTrue(biasValue >= -1 && biasValue <= 1.0);
	}
	
	@Test
	public void constructorOneArgsCreatesOnlyBiasFromWeightGen()
	{
		
	}

}
