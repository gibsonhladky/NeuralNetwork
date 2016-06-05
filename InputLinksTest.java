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
		final double biasValue = testLink.inputValue();
		
		assertTrue(biasValue >= -1 && biasValue <= 1.0);
	}
	
	@Test
	public void constructorOneArgsCreatesOnlyBiasFromWeightGenerator()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
		final double actualBiasValue = testLink.inputValue();
		
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
		
		final double actualInputValue = testLink.inputValue();
		
		final double expectedInputValue = 2.0;
		assertEquals(expectedInputValue, actualInputValue, DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputZeroError()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
		
		final double expectedInputValue = testLink.inputValue();
		testLink.adjustToErrorGivenOutput(0, 1);
		assertEquals(expectedInputValue, testLink.inputValue(), DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputZeroOutput()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
		
		final double expectedInputValue = testLink.inputValue();
		testLink.adjustToErrorGivenOutput(1, 0);
		assertEquals(expectedInputValue, testLink.inputValue(), DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputCalculations()
	{
		InputLinks testLink = new InputLinks(new MockWeightGenerator());
		testLink.adjustToErrorGivenOutput(1, 1);
		
		final double expectedInputValue = 1.1;
		assertEquals(expectedInputValue, testLink.inputValue(), DELTA);
	}

}
