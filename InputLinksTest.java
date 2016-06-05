import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import neuralNetwork.InputPerceptron;
import neuralNetwork.Perceptron;
import neuralNetwork.InputLinks;

public class InputLinksTest {
	
	final double DELTA = 0.001;

	@Test
	public void constructorNoArgsCreatesOnlyRandomBias()
	{
		InputLinks testLink = new InputLinks();
		final double[] actualInputs = testLink.inputValues();
		
		final int expectedLength = 1;
		assertEquals(expectedLength, actualInputs.length);
		
		final double biasValue = actualInputs[0];
		assertTrue(biasValue >= -1 && biasValue <= 1.0);
	}
	
	@Test
	public void constructorOneArgsCreatesOnlyBiasFromWeightGenerator()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
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
		
		InputLinks testLink = new InputLinks(new MockWeightGenerator(), inputs);
		
		final double[] actualInputs = testLink.inputValues();
		
		final double[] expectedInputValues = {1.0, 0.5, 0.5};
		assertArrayEquals(expectedInputValues, actualInputs, DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputZeroError()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
		
		final double[] expectedInputs = testLink.inputValues();
		testLink.adjustToErrorGivenOutput(0, 1);
		assertArrayEquals(expectedInputs, testLink.inputValues(), DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputZeroOutput()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
		
		final double[] expectedInputs = testLink.inputValues();
		testLink.adjustToErrorGivenOutput(1, 0);
		assertArrayEquals(expectedInputs, testLink.inputValues(), DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputCalculations()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
		testLink.adjustToErrorGivenOutput(1, 1);
		
		final double[] expectedInputs = {1.1};
		assertArrayEquals(expectedInputs, testLink.inputValues(), DELTA);
	}

}
