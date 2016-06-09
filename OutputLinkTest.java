import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.OutputLink;
import neuralNetwork.Perceptron;
import neuralNetwork.PerceptronLink;

public class OutputLinkTest {

	private final double DELTA = 0.1;
	private final Perceptron mockP = new MockPerceptron();
	
	private OutputLink testLinks;
	private Perceptron sourceP = new MockPerceptron();
	
	@Before
	public void setup()
	{
		testLinks = new OutputLink(sourceP);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNull()
	{
		testLinks = new OutputLink(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addLinkHandlesNull()
	{
		testLinks.addLink(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addLinkChecksForCorrectFromPerceptron()
	{
		PerceptronLink testLink = new PerceptronLink(mockP, mockP, 0);
		testLinks.addLink(testLink);
	}
	
	@Test
	public void getTotalErrorCalculatesCorrectlyWithNoLinks()
	{
		final double error = testLinks.totalError();
		final double expectedError = 0;
		assertEquals(expectedError, error, DELTA);
	}
	
	@Test
	public void getTotalErrorCalculateCorrectlyWithLinks()
	{
		PerceptronLink link1 = new PerceptronLink(sourceP, mockP, 0.9);
		PerceptronLink link2 = new PerceptronLink(sourceP, mockP, 0.3);
		
		testLinks.addLink(link1);
		testLinks.addLink(link2);
		
		final double expectedError = 0.6;
		assertEquals(expectedError, testLinks.totalError(), DELTA);
	}

}
