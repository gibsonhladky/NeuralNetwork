import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.InputLayer;
import neuralNetwork.NetworkLayer;
import neuralNetwork.Perceptron;

public class InputLayerTest {
	
	private final double DELTA = 0.001;
	private final double[] testInputs = {0, 1};
	
	private InputLayer testLayer;
	
	@Before
	public void setup()
	{
		testLayer = new InputLayer(2);
	}
	
	private void activateOnTestInputs()
	{
		testLayer.setInputs(testInputs);
		testLayer.activate();
	}
	
	private double[] getOutputs()
	{
		Perceptron[] perceptrons = testLayer.perceptrons();
		double[] actualOutputs = new double[perceptrons.length];
		for(int i = 0; i < perceptrons.length; i++)
		{
			actualOutputs[i] = perceptrons[i].output();
		}
		return actualOutputs;
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesSmallSize()
	{
		testLayer = new InputLayer(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooSmall()
	{
		double[] testInputs = {0};
		testLayer.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setInputsTooLarge()
	{
		double[] testInputs = {0, 0, 0};
		testLayer.setInputs(testInputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNextLayerHandlesNull()
	{
		testLayer.addNextLayer(null);
	}
	
	@Test
	public void sizeReturnsCorrectValue()
	{
		final int expectedSize = 2;
		assertEquals(expectedSize, testLayer.size());
	}
	
	@Test
	public void nextLayersReturnsCorrectLayers()
	{
		MockLayer ml1 = new MockLayer(1);
		MockLayer ml2 = new MockLayer(1);
		List<NetworkLayer> expectedLayers = new ArrayList<NetworkLayer>(1);
		expectedLayers.add(ml1);
		expectedLayers.add(ml2);
		
		testLayer.addNextLayer(ml1);
		testLayer.addNextLayer(ml2);
		
		assertEquals(expectedLayers, testLayer.nextLayers());
	}
	
	@Test
	public void activateCorrectlyUpdatesBasedOnInputs()
	{
		activateOnTestInputs();
		
		Perceptron[] perceptrons = testLayer.perceptrons();
		double[] actualOutputs = new double[perceptrons.length];
		for(int i = 0; i < perceptrons.length; i++)
		{
			actualOutputs[i] = perceptrons[i].output();
		}
		assertArrayEquals(testInputs, actualOutputs, DELTA);
	}
	
	@Test
	public void calculateErrorDoesNothing()
	{
		activateOnTestInputs();
		testLayer.calculateError();
		
		double[] actualOutputs = getOutputs();
		assertArrayEquals(testInputs, actualOutputs, DELTA);
		
		testLayer.activate();
		actualOutputs = getOutputs();
		assertArrayEquals(testInputs, actualOutputs, DELTA);
	}
	
	@Test
	public void adjustToErrorDoesNothing()
	{
		activateOnTestInputs();
		testLayer.adjustToError();
		
		double[] actualOutputs = getOutputs();
		assertArrayEquals(testInputs, actualOutputs, DELTA);
		
		testLayer.activate();
		actualOutputs = getOutputs();
		assertArrayEquals(testInputs, actualOutputs, DELTA);
	}
	
	@Test
	public void calculateErrorAndAdjustToErrorDoNothing()
	{
		activateOnTestInputs();
		
		testLayer.calculateError();
		testLayer.adjustToError();
		
		double[] actualOutputs = getOutputs();
		assertArrayEquals(testInputs, actualOutputs, DELTA);
		
		testLayer.activate();
		actualOutputs = getOutputs();
		assertArrayEquals(testInputs, actualOutputs, DELTA);
	}

}
