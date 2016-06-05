import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.OutputPerceptron;
import neuralNetwork.WeightGenerator;

public class OutputPerceptronTest {

	final double DELTA = 0.001;
	
	final WeightGenerator mockGen = new MockWeightGenerator();
	
	@Test (expected = IllegalArgumentException.class)
	public void negativeInputSize()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void zeroInputSize()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooSmall()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		double[] testInputs = {1.0};
		testP.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooLarge()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		double[] testInputs = {1.0, 1.0, 1.0};
		testP.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setWeightsTooSmall()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		double[] testWeights = {1.0};
		testP.setWeights(testWeights);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setWeightsTooLarge()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		double[] testWeights = {1.0, 1.0, 1.0};
		testP.setWeights(testWeights);
	}
	
	@Test
	public void correctOutput() 
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		double[] testInputs = {0.5, 0.5};
		double[] testWeights = {1.0, -1.0};
		
		testP.setInputs(testInputs);
		testP.setWeights(testWeights);
		
		testP.activate();
		
		final double expectedOutput = 0.5;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void correctError()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		double[] testInputs = {0.5, 0.5};
		double[] testWeights = {1.0, -1.0};
		
		testP.setInputs(testInputs);
		testP.setWeights(testWeights);
		
		testP.activate();
		
		final double trainingExpectedOutput = 1.0;
		testP.setExpectedOutput(trainingExpectedOutput);
		testP.adjustToError();
		
		final double expectedOutput = 0.5;
		assertEquals(expectedOutput, testP.error(), DELTA);
	}
	
	@Test
	public void correctErrorAdjustment()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		double[] testInputs = {0.5, 0.5};
		double[] testWeights = {1.0, -1.0};
		
		testP.setInputs(testInputs);
		testP.setWeights(testWeights);
		
		testP.activate();
		
		final double trainingExpectedOutput = 1.0;
		testP.setExpectedOutput(trainingExpectedOutput);
		testP.adjustToError();
		testP.activate();
		
		final double expectedOutput = 0.506250;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}

}
