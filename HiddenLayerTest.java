

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenLayer;
import neuralNetwork.NetworkLayer;
import neuralNetwork.Perceptron;

public class HiddenLayerTest {

	private final double DELTA = 0.001;
	
	private HiddenLayer testLayer;
	
	@Before
	public void setUp()
	{
		testLayer = new HiddenLayer(2, new MockWeightGenerator());
	}
	
	private double[] getOutputs()
	{
		Perceptron[] perceptrons = testLayer.perceptrons();
		double[] outputs = new double[perceptrons.length];
		for(int i = 0; i < outputs.length; i++)
		{
			outputs[i] = perceptrons[i].output();
		}
		return outputs;
	}
	
	private double[] getErrors()
	{
		Perceptron[] perceptrons = testLayer.perceptrons();
		double[] errors = new double[perceptrons.length];
		for(int i = 0; i < errors.length; i++)
		{
			errors[i] = perceptrons[i].error();
		}
		return errors;
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesInvalidSize() {
		testLayer = new HiddenLayer(0, new MockWeightGenerator());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullWeightGenerator() {
		testLayer = new HiddenLayer(1, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addPreviousLayerHandlesNull()
	{
		testLayer.appendTo(null);
	}
	
	@Test
	public void getPreviousLayersReturnsCorrectLayers()
	{
		MockLayer ml1 = new MockLayer(1);
		MockLayer ml2 = new MockLayer(1);
		List<NetworkLayer> expectedLayers = new ArrayList<NetworkLayer>(2);
		expectedLayers.add(ml1);
		expectedLayers.add(ml2);
		
		testLayer.appendTo(ml1);
		testLayer.appendTo(ml2);
		
		assertEquals(expectedLayers, testLayer.previousLayers());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addNextLayersHandlesNull()
	{
		testLayer.addNextLayer(null);
	}
	
	@Test
	public void getNextLayersReturnsCorrectLayers()
	{
		MockLayer ml1 = new MockLayer(1);
		MockLayer ml2 = new MockLayer(1);
		List<NetworkLayer> expectedLayers = new ArrayList<NetworkLayer>(2);
		expectedLayers.add(ml1);
		expectedLayers.add(ml2);
		
		testLayer.addNextLayer(ml1);
		testLayer.addNextLayer(ml2);
		
		assertEquals(expectedLayers, testLayer.nextLayers());
	}
	
	@Test
	public void activateCorrectlyActivatesAllPerceptrons()
	{
		testLayer.appendTo(new MockLayer(2));
		testLayer.activate();
		
		double[] expectedOutputs = {0.8807970779, 0.8807970779};
		assertArrayEquals(expectedOutputs, getOutputs(), DELTA);
	}
	
	@Test
	public void calculateErrorCorrectlyCalculatesForAllPerceptrons()
	{
		testLayer.addNextLayer(new MockLayer(2));
		testLayer.activate();
		testLayer.calculateError();
		
		double[] expectedErrors = {1.0, 1.0};
		assertArrayEquals(expectedErrors, getErrors(), DELTA);
	}
	
	@Test
	public void adjustErrorCorrectlyChangesInputs()
	{
		testLayer.appendTo(new MockLayer(2));
		testLayer.addNextLayer(new MockLayer(2));
		testLayer.activate();
		testLayer.calculateError();
		testLayer.adjustToError();
		testLayer.activate();
		
		double[] expectedOutputs = {0.8980880967, 0.8980880967};
		assertArrayEquals(expectedOutputs, getOutputs(), DELTA);
	}

}
