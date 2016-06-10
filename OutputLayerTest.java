import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.NetworkLayer;
import neuralNetwork.OutputLayer;
import neuralNetwork.Perceptron;

public class OutputLayerTest {

	private final double DELTA = 0.1;
	
	OutputLayer testLayer;
	
	@Before
	public void setUp() {
		testLayer = new OutputLayer(2, new MockWeightGenerator());
		testLayer.appendTo(new MockLayer(2));
	}
	
	private double[] getErrors()
	{
		Perceptron[] perceptrons = testLayer.perceptrons();
		double[] errors = new double[perceptrons.length];
		for(int i = 0; i < perceptrons.length; i++)
		{
			errors[i] = perceptrons[i].error();
		}
		return errors;
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
		testLayer.appendTo(null);
	}

	@Test
	public void activateProducesCorrectOutputs() {
		testLayer.activate();
		
		double[] expectedOutputs = {0.8807970779, 0.8807970779};
		assertArrayEquals(expectedOutputs, testLayer.getOutputs(), DELTA);
	}
	
	@Test
	public void sizeReturnsCorrectValue()
	{
		final int expectedSize = 2;
		assertEquals(expectedSize, testLayer.size());
	}
	
	@Test
	public void calculateErrorProducesCorrectErrors()
	{
		double[] expectedTrainingOutputs = {1.0, 0.0};
		testLayer.setExpectedOutputs(expectedTrainingOutputs);
		testLayer.activate();
		testLayer.calculateError();
		
		double[] expectedErrors = {0.119203, -0.8807970779};
		assertArrayEquals(expectedErrors, getErrors(), DELTA);
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
	
	@Test
	public void returnsCorrectPreviousLayers()
	{
		NetworkLayer previous1 = new MockLayer(2);
		NetworkLayer previous2 = new MockLayer(2);
		List<NetworkLayer> expectedLayers = new ArrayList<NetworkLayer>();
		expectedLayers.add(previous1);
		expectedLayers.add(previous2);
		
		testLayer = new OutputLayer(2, new MockWeightGenerator());
		testLayer.appendTo(previous1);
		testLayer.appendTo(previous2);
		
		assertEquals(expectedLayers, testLayer.previousLayers());
	}

}
