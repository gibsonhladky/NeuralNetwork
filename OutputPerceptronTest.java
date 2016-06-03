import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.OutputPerceptron;

public class OutputPerceptronTest {

	final double DELTA = 0.001;
	
	@Test (expected = IllegalArgumentException.class)
	public void negativeInputSize()
	{
		OutputPerceptron testP = new OutputPerceptron(-1);
	}
	
	@Test
	public void correctOutput() {
		OutputPerceptron testP = new OutputPerceptron(2);
		double[] testInputs = {0.5, 0.5};
		double[] testWeights = {1.0, -1.0};
		
		testP.setInputs(testInputs);
		testP.setWeights(testWeights);
		
		testP.activate();
		
		final double expectedOutput = 0.5;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooSmall()
	{
		OutputPerceptron testP = new OutputPerceptron(2);
		double[] testInputs = {1.0};
		testP.setInputs(testInputs);
	}

}
