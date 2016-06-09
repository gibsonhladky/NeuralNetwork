import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.Perceptron;
import neuralNetwork.PerceptronLink;

public class HiddenPerceptronTest {

	private final double DELTA = 0.001;
	private final Perceptron mockP = new MockPerceptron();
	
	private HiddenPerceptron testP;
	
	@Before
	public void setup()
	{
		testP = new HiddenPerceptron(new MockWeightGenerator());
		PerceptronLink inLink1 = new PerceptronLink(mockP, testP, 1);
		PerceptronLink inLink2 = new PerceptronLink(mockP, testP, 1);
		testP.addInputLink(inLink1);
		testP.addInputLink(inLink2);
		
		PerceptronLink outLink1 = new PerceptronLink(testP, mockP, 1);
		PerceptronLink outLink2 = new PerceptronLink(testP, mockP, 1);
		testP.addOutputLink(outLink1);
		testP.addOutputLink(outLink2);
	}
	
	@Test
	public void outputAndErrorDoNotChangeState()
	{
		final double priorOutput = testP.output();
		final double expectedError = testP.error();
		
		assertEquals(priorOutput, testP.output(), DELTA);
		assertEquals(expectedError, testP.error(), DELTA);
	}
	
	@Test
	public void activateCorrectlyChangesOutput()
	{	
		testP.activate();
		
		final double expectedOutput = 0.8807971;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void activateChangesOutputOnlyOnNewInputs()
	{
		final double priorOutput = testP.output();
		testP.activate();
		
		assertNotEquals(priorOutput, testP.output(), DELTA);
	}
	
	@Test
	public void activateDoesNotChangeError()
	{
		final double priorError = testP.error();
		testP.activate();
		
		assertEquals(priorError, testP.error(), DELTA);
	}
	
	@Test
	public void calculateErrorDoesNotChangeOutput()
	{
		final double priorOutput = testP.output();
		testP.calculateError();
		
		assertEquals(priorOutput, testP.output(), DELTA);
	}
	
	@Test
	public void calculateErrorCorrectlyChangesError()
	{
		testP.calculateError();
		
		final double expectedError = 1.0;
		assertEquals(expectedError, testP.error(), DELTA);
	}
	
	@Test
	public void adjustToErrorCorrectlyUpdatesInputs()
	{
		testP.activate();
		testP.calculateError();
		testP.adjustToError();
		testP.activate();
		
		final double expectedOutput = 0.8980880967;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullInput()
	{
		testP = new HiddenPerceptron(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addInputLinkHandlesNullInput()
	{
		testP.addInputLink(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addOutputLinkHandlesNullInput()
	{
		testP.addOutputLink(null);
	}
	
}
