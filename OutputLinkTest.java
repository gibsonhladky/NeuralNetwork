import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.OutputLink;

public class OutputLinkTest {

	static final double DELTA = 0.1;
	
	@Test
	public void getErrorReturnsNothingFromEmptyList() {
		OutputLink testLink = new OutputLink();
		
		final double error = testLink.getAssociatedError();
		final double expectedError = 0;
		assertEquals(expectedError, error, DELTA);
	}
	
	@Test
	public void getErrorSingleLink()
	{
		OutputLink testLink = new OutputLink();
		testLink.addLink(new MockPerceptron(), new Double(1.0));
		
		final double error = testLink.getAssociatedError();
		final double expectedError = 0.5;
		assertEquals(expectedError, error, DELTA);
	}
	
	@Test
	public void getErrorMultipleLink()
	{
		OutputLink testLink = new OutputLink();
		for(int i = 0; i < 3; i++)
		{
			testLink.addLink(new MockPerceptron(), new Double(i));
		}
		
		final double error = testLink.getAssociatedError();
		final double expectedError = 1.5;
		assertEquals(expectedError, error, DELTA);
	}

}
