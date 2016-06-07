import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.OutputLink;

public class OutputLinkTest {

	static final double DELTA = 0.1;
	
	@Test
	public void getAssociatedErrorNoLinks() {
		OutputLink testLink = new OutputLink(new MockWeightGenerator());
		
		final double error = testLink.getAssociatedError();
		final double expectedError = 0;
		assertEquals(expectedError, error, DELTA);
	}
	
	@Test
	public void getAssociatedErrorSingleLink()
	{
		OutputLink testLink = new OutputLink(new MockWeightGenerator());
		testLink.addLink(new MockPerceptron());
		
		final double error = testLink.getAssociatedError();
		final double expectedError = 0.5;
		assertEquals(expectedError, error, DELTA);
	}
	
	@Test
	public void getAssociatedErrorMultipleLink()
	{
		OutputLink testLink = new OutputLink(new MockWeightGenerator());
		for(int i = 0; i < 3; i++)
		{
			testLink.addLink(new MockPerceptron());
		}
		
		final double error = testLink.getAssociatedError();
		final double expectedError = 1.5;
		assertEquals(expectedError, error, DELTA);
	}

}
