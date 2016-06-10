import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.InputPerceptron;

public class InputPerceptronTest {

	final double DELTA = 0.001;
	
	private InputPerceptron testP;
	
	@Before
	public void setup()
	{
		testP = new InputPerceptron();
		testP.setInput(1.0);
	}
	
	@Test
	public void setInputChangesOutput() {
		final double expectedOutput = 1.0;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void doesNotHaveError()
	{
		testP.calculateError();
		
		final double expectedError = 0;
		assertEquals(expectedError, testP.error(), DELTA);
	}
	
	@Test
	public void doesNotAdjustToError()
	{
		testP.adjustToError();
		testP.activate();
		
		final double expectedOutput = 1.0;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}

}
