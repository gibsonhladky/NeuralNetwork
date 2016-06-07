import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.Perceptron;

public class HiddenPerceptronTest {

	private final double DELTA = 0.001;
	
	@Test
	public void outputDoesNotChangeState()
	{
		HiddenPerceptron testP = new HiddenPerceptron(new MockWeightGenerator());
		final double expectedOutput = testP.output();
		
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void activateCorrectlyChangesOutput()
	{
		HiddenPerceptron testP = new HiddenPerceptron(new MockWeightGenerator());
		testP.addInputLink(new MockPerceptron());
		testP.addInputLink(new MockPerceptron());
		
		testP.activate();
		
		final double expectedOutput = 0.8807971;
		assertEquals(expectedOutput, testP.output(), DELTA);
	}
	
	@Test
	public void activateChangesOutput()
	{
		
	}
	
}
