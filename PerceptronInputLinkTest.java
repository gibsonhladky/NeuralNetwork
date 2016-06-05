import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import neuralNetwork.InputPerceptron;
import neuralNetwork.Perceptron;
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
	public void constructorOneArgsCreatesOnlyBiasFromWeightGenerator()
	{
		PerceptronInputLink testLink = new PerceptronInputLink(new MockWeightGenerator());
		final double[] actualInputs = testLink.inputValues();
		
		final int expectedLength = 1;
		assertEquals(expectedLength, actualInputs.length);
		
		final double actualBiasValue = actualInputs[0];
		final double expectedBiasValue = 1;
		assertEquals(expectedBiasValue, actualBiasValue, DELTA);
	}
	
	@Test
	public void constructorTwoArgsCreatesBiasAndAllWeightsFromWeightGenerator()
	{
		ArrayList<Perceptron> inputs = new ArrayList<Perceptron>(2);
		inputs.add(new MockPerceptron());
		inputs.add(new MockPerceptron());
		
		PerceptronInputLink testLink = new PerceptronInputLink(new MockWeightGenerator(), inputs);
		
		final double[] actualInputs = testLink.inputValues();
		
		final int expectedLength = 3;
		assertEquals(expectedLength, actualInputs.length);
		
		final double[] expectedInputValues = {1.0, 0.5, 0.5};
		assertArrayEquals(expectedInputValues, actualInputs, DELTA);
	}

}
