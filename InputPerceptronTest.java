import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.InputPerceptron;

public class InputPerceptronTest {

	final double DELTA = 0.001;
	
	@Test
	public void activateSetsOutputToInput() {
		InputPerceptron testPerceptron = new InputPerceptron();
		final double testInput = 1.0;
		testPerceptron.setInput(testInput);
		testPerceptron.activate();
		
		assertEquals(testInput, testPerceptron.output(), DELTA);
	}
	
	@Test
	public void doesNotHaveError()
	{
		InputPerceptron testPerceptron = new InputPerceptron();
		final double testInput = 1.0;
		testPerceptron.setInput(testInput);
		testPerceptron.activate();
		
		testPerceptron.adjustToError();
		
		final double expectedError = 0;
		assertEquals(expectedError, testPerceptron.error(), DELTA);
	}
	
	@Test
	public void doesNotAdjustToError()
	{
		InputPerceptron testPerceptron = new InputPerceptron();
		final double testInput = 1.0;
		testPerceptron.setInput(testInput);
		testPerceptron.activate();
		
		testPerceptron.adjustToError();
		
		testPerceptron.activate();
		
		assertEquals(testInput, testPerceptron.output(), DELTA);
	}

}
