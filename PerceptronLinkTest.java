import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.Perceptron;
import neuralNetwork.PerceptronLink;
import neuralNetwork.WeightGenerator;

public class PerceptronLinkTest {

	private final Perceptron mockFrom = new MockPerceptron();
	private final Perceptron mockTo = new MockPerceptron();
	private final double DELTA = 0.001;
	
	private PerceptronLink testLink;
	
	@Before
	public void setUp()
	{
		testLink = new PerceptronLink(mockFrom, mockTo, 0.5);
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullFromPerceptron() {
		testLink = new PerceptronLink(null, mockTo, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullToPerceptron() {
		testLink = new PerceptronLink(mockFrom, null, 1);
	}
	
	@Test
	public void constructorHandlesZero() {
		testLink = new PerceptronLink(mockFrom, mockTo, 0);
	}
	
	@Test
	public void fromReturnsCorrectPerceptron()
	{
		assertEquals(mockFrom, testLink.from());
	}
	
	@Test
	public void toReturnsCorrectPerceptron()
	{
		assertEquals(mockTo, testLink.to());
	}
	
	@Test
	public void weightedOutputReturnsCorrectValue()
	{
		final double expectedOutput = 0.25;
		assertEquals(expectedOutput, testLink.weightedOutput(), DELTA);
	}
	
	@Test
	public void weightedErrorReturnsCorrectValue()
	{
		final double expectedError = 0.25;
		assertEquals(expectedError, testLink.weightedError(), DELTA);
	}
	
	@Test
	public void updateWeightByCorrectlyModfiesWeight()
	{
		final double updateAmount = 0.1;
		testLink.updateWeightBy(updateAmount);
		
		final double expectedOutput = 0.3;
		assertEquals(expectedOutput, testLink.weightedOutput(), DELTA);
		final double expectedError = 0.3;
		assertEquals(expectedError, testLink.weightedError(), DELTA);
	}

}
