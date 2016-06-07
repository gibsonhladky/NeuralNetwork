import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.Perceptron;

public class HiddenPerceptronTest {

	private final double DELTA = 0.001;
	
	HiddenPerceptron testP;
	
	@Before
	public void setup()
	{
		testP = new HiddenPerceptron(new MockWeightGenerator());
		testP.addInputLink(new MockPerceptron());
		testP.addInputLink(new MockPerceptron());
		testP.addOutputLink(new MockPerceptron());
		testP.addOutputLink(new MockPerceptron());
	}
	
	@Test
	public void outputAndErrorDoNotChangeState()
	{
		final double expectedOutput = testP.output();
		final double expectedError = testP.error();
		
		assertEquals(expectedOutput, testP.output(), DELTA);
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
	public void activateChangesOutputOnNewInputs()
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
	public void adjustToErrorDoesNotChangeOutput()
	{
		final double priorOutput = testP.output();
		testP.adjustToError();
		
		assertEquals(priorOutput, testP.output(), DELTA);
	}
	
	@Test
	public void adjustToErrorCorrectlyChangesError()
	{
		testP.adjustToError();
		
		final double expectedError = 1.0;
		assertEquals(expectedError, testP.error(), DELTA);
	}
	
}
