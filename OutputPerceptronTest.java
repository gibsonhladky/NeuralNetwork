import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.OutputPerceptron;
import neuralNetwork.PerceptronLink;
import neuralNetwork.WeightGenerator;

public class OutputPerceptronTest {

	private final double DELTA = 0.001;
	private final WeightGenerator mockGen = new MockWeightGenerator();
	private final MockPerceptron mockP = new MockPerceptron();
	
	private OutputPerceptron testP;
	
	@Before
	public void setup()
	{
		testP = new OutputPerceptron(mockGen);
		PerceptronLink link1 = new PerceptronLink(mockP, testP, 1);
		PerceptronLink link2 = new PerceptronLink(mockP, testP, 1);
		testP.addInputLink(link1);
		testP.addInputLink(link2);
		testP.setExpectedOutput(1);
		testP.activate();
		testP.calculateError();
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
		final double expectedOutput = 0.119203;
		assertEquals(expectedOutput, testP.error(), DELTA);
	}
	
	@Test
	public void adjustToErrorCorrectlyUpdatesInputs()
	{
		testP.adjustToError();
		testP.activate();
		
		final double expectedOutput = 0.8829842;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void addOutputLinkDoesNothing()
	{
		PerceptronLink outputLink = new PerceptronLink(testP, mockP, 1);
		testP.addOutputLink(outputLink);
		// Reassert previous tests for same expected results.
		activateProducesCorrectlySetsOutput();
		calculateErrorCorrectlySetsError();
		adjustToErrorCorrectlyUpdatesInputs();
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
