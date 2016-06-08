import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.OutputLayer;

public class OutputLayerTest {

	private final double DELTA = 0.1;
	
	OutputLayer testLayer;
	
	@Before
	public void setUp() {
		testLayer = new OutputLayer(2, new MockWeightGenerator());
		testLayer.addPreviousLayer(new MockLayer(2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesInvalidSize()
	{
		testLayer = new OutputLayer(0, new MockWeightGenerator());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullWeightGenerator()
	{
		testLayer = new OutputLayer(1, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addPreviousLayerHandlesNull()
	{
		testLayer.addPreviousLayer(null);
	}

	@Test
	public void activateProducesCorrectOutputs() {
		testLayer.activate();
		
		double[] expectedOutputs = {0.8807970779, 0.8807970779};
		assertArrayEquals(expectedOutputs, testLayer.getOutputs(), DELTA);
	}
	
	@Test
	public void correctlyAdjustsToError()
	{
		double[] expectedTrainingOutputs = {1.0, 0.0};
		testLayer.setExpectedOutputs(expectedTrainingOutputs);
		testLayer.activate();
		testLayer.calculateError();
		testLayer.adjustToError();
		testLayer.activate();
		
		double[] expectedOutputs = {0.8829842, 0.882042963};
		assertArrayEquals(expectedOutputs, testLayer.getOutputs(), DELTA);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setExpectedOutputsHandlesNull()
	{
		testLayer.setExpectedOutputs(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setExpectedOutputsHandlesSmallArray()
	{
		double[] smallExpectedOutputs = {0.0};
		testLayer.setExpectedOutputs(smallExpectedOutputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setExpectedOutputsHandlesLargeArray()
	{
		double[] largeExpectedOutputs = {0.0, 0.0, 0.0};
		testLayer.setExpectedOutputs(largeExpectedOutputs);
	}

}
