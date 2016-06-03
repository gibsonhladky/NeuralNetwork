import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.InputPerceptron;

public class InputPerceptronTest {

	final double DELTA = 0.001;
	
	@Test
	public void activateSetsOutputToInput() {
		InputPerceptron testPerceptron = new InputPerceptron(0);
		final double testInput = 1.0;
		testPerceptron.setInput(testInput);
		testPerceptron.activate();
		
		assertEquals(testInput, testPerceptron.output(), DELTA);
	}
	
	@Test
	public void doesNotAdjustToError()
	{
		InputPerceptron testPerceptron = new InputPerceptron(0);
		final double testInput = 1.0;
		testPerceptron.setInput(testInput);
		testPerceptron.activate();
		
		testPerceptron.calculateDeltas();
		testPerceptron.updateWeights();
		
		testPerceptron.activate();
		
		assertEquals(testInput, testPerceptron.output(), DELTA);
	}

}
