import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.OutputPerceptron;
import neuralNetwork.PerceptronLink;
import neuralNetwork.WeightGenerator;

public class OutputPerceptronTest {

	final double DELTA = 0.001;
	
	final WeightGenerator mockGen = new MockWeightGenerator();
	
	OutputPerceptron testP;
	
	@Before
	public void setup()
	{
		testP = new OutputPerceptron(mockGen);
		PerceptronLink link1 = new PerceptronLink(new MockPerceptron(), testP, 1);
		PerceptronLink link2 = new PerceptronLink(new MockPerceptron(), testP, 1);
		testP.addInputLink(link1);
		testP.addInputLink(link2);
		testP.activate();
	}
	
	@Test
	public void activateProducesCorrectlySetsOutput() 
	{
		final double expectedOutput = 0.8807971;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void calculateErrorCorrectlySetsError()
	{
		final double trainingExpectedOutput = 1.0;
		testP.setExpectedOutput(trainingExpectedOutput);
		testP.calculateError();
		
		final double expectedOutput = 0.119203;
		assertEquals(expectedOutput, testP.error(), DELTA);
	}
	
	@Test
	public void adjustToErrorCorrectlyUpdatesInputs()
	{
		final double trainingExpectedOutput = 1.0;
		testP.setExpectedOutput(trainingExpectedOutput);
		testP.calculateError();
		testP.adjustToError();
		testP.activate();
		
		final double expectedOutput = 0.8829842;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullInput()
	{
		testP = new OutputPerceptron(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addInputHandlesNullInput()
	{
		testP.addInputLink(null);
	}

}
