import static org.junit.Assert.*;

import org.junit.Test;

import neuralNetwork.OutputPerceptron;
import neuralNetwork.WeightGenerator;

public class OutputPerceptronTest {

	final double DELTA = 0.001;
	
	final WeightGenerator mockGen = new MockWeightGenerator();
	
	
	@Test
	public void correctOutput() 
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		testP.addInput(new MockPerceptron());
		testP.addInput(new MockPerceptron());
		
		testP.activate();
		
		final double expectedOutput = 0.8807971;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void correctError()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		testP.addInput(new MockPerceptron());
		testP.addInput(new MockPerceptron());
		
		testP.activate();
		
		final double trainingExpectedOutput = 1.0;
		testP.setExpectedOutput(trainingExpectedOutput);
		testP.adjustToError();
		
		final double expectedOutput = 0.119203;
		assertEquals(expectedOutput, testP.error(), DELTA);
	}
	
	@Test
	public void correctErrorAdjustment()
	{
		OutputPerceptron testP = new OutputPerceptron(mockGen);
		testP.addInput(new MockPerceptron());
		testP.addInput(new MockPerceptron());
		
		testP.activate();
		
		final double trainingExpectedOutput = 1.0;
		testP.setExpectedOutput(trainingExpectedOutput);
		testP.adjustToError();
		// weights = 1.010499
		testP.activate();
		
		final double expectedOutput = 0.8829842;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}

}
