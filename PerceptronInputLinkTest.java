import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.PerceptronInputLink;

public class PerceptronInputLinkTest {
	
	final double DELTA = 0.001;

	@Test
	public void constructorNoArgsCreatesOnlyRandomBias()
	{
		PerceptronInputLink testLink = new PerceptronInputLink();
		final double[] actualInputs = testLink.inputValues();
		
		final int expectedLength = 1;
		assertEquals(expectedLength, actualInputs.length);
		
		final double biasValue = actualInputs[0];
		assertTrue(biasValue >= -1 && biasValue <= 1.0);
	}
	
	@Test
	public void constructorOneArgsCreatesOnlyBiasFromWeightGen()
	{
		PerceptronInputLink testLink = new PerceptronInputLink(new MockWeightGenerator());
		final double[] actualInputs = testLink.inputValues();
		
		final int expectedLength = 1;
		assertEquals(expectedLength, actualInputs.length);
		
		final double actualBiasValue = actualInputs[0];
		final double expectedBiasValue = 1;
		assertEquals(expectedBiasValue, actualBiasValue, DELTA);
	}

}
