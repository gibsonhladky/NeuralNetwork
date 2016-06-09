import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.Perceptron;
import neuralNetwork.PerceptronLink;
import neuralNetwork.WeightGenerator;
import neuralNetwork.InputLinks;

public class InputLinksTest {
	
	final double DELTA = 0.001;
	final Perceptron source = new MockPerceptron();
	final WeightGenerator wg = new MockWeightGenerator();
	
	private InputLinks testLink;
	
	@Before
	public void setup()
	{
		testLink = new InputLinks(source, wg);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullSource()
	{
		testLink = new InputLinks(null, wg);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullWeightGenerator()
	{
		testLink = new InputLinks(source, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addLinkHandlesNullLink()
	{
		testLink.addLink(null);;
	}
	
	@Test
	public void adjustToErrorGivenOutputZeroError()
	{
		final double expectedInputValue = testLink.totalInput();
		testLink.adjustToErrorGivenOutput(0, 1);
		assertEquals(expectedInputValue, testLink.totalInput(), DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputZeroOutput()
	{
		final double expectedInputValue = testLink.totalInput();
		testLink.adjustToErrorGivenOutput(1, 0);
		assertEquals(expectedInputValue, testLink.totalInput(), DELTA);
	}
	
	@Test
	public void adjustToErrorGivenOutputZeroOutputZeroError()
	{
		final double expectedInputValue = testLink.totalInput();
		testLink.adjustToErrorGivenOutput(0, 0);
		assertEquals(expectedInputValue, testLink.totalInput(), DELTA);
	}
	
	@Test
	public void constructorCreatesBias()
	{
		final double actualInput = testLink.totalInput();
		
		final double expectedInput = 1;
		assertEquals(expectedInput, actualInput, DELTA);
	}
	
	@Test
	public void addLinkCorrectlyInsertsALink()
	{
		addLinkToMock();
		final double expectedInput = 1.5;
		assertEquals(expectedInput, testLink.totalInput(), DELTA);
	}
	
	@Test
	public void adjustToErrorCorrectlyAdjustsWeights()
	{
		addLinkToMock();
		testLink.adjustToErrorGivenOutput(0.5, 0.5);
		
		final double expectedInputValue = 1.5375;
		assertEquals(expectedInputValue, testLink.totalInput(), DELTA);
	}
	
	private void addLinkToMock()
	{
		PerceptronLink l = new PerceptronLink(source, new MockPerceptron(), 1);
		testLink.addLink(l);
	}

}
