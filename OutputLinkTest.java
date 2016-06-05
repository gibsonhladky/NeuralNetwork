import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.OutputLink;

public class OutputLinkTest {

	static final double DELTA = 0.1;
	
	@Test
	public void getErrorReturnsNothingFromEmptyList() {
		OutputLink testLink = new OutputLink();
		
		final double error = testLink.getError();
		final double expectedError = 0;
		assertEquals(expectedError, error, DELTA);
	}

}
