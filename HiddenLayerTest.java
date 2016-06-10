

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenLayer;
import neuralNetwork.Perceptron;

public class HiddenLayerTest {

	private final double DELTA = 0.001;
	private final MockLayer mockLayer1 = new MockLayer(2);
	private final MockLayer mockLayer2 = new MockLayer(2);
	
	private HiddenLayer testLayer;
	
	@Before
	public void setUp()
	{
		testLayer = new HiddenLayer(2, new MockWeightGenerator());
		testLayer.appendTo(mockLayer1);
		mockLayer2.appendTo(testLayer);
		testLayer.activate();
		testLayer.calculateError();
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
	public void activateCorrectlyActivatesAllPerceptrons()
	{
		double[] expectedOutputs = {0.8807970779, 0.8807970779};
		assertArrayEquals(expectedOutputs, getOutputs(), DELTA);
	}
	
	@Test
	public void calculateErrorCorrectlyCalculatesForAllPerceptrons()
	{
		double[] expectedErrors = {1.0, 1.0};
		assertArrayEquals(expectedErrors, getErrors(), DELTA);
	}
	
	@Test
	public void adjustErrorCorrectlyChangesInputs()
	{
		testLayer.adjustToError();
		testLayer.activate();
		
		double[] expectedOutputs = {0.8980880967, 0.8980880967};
		assertArrayEquals(expectedOutputs, getOutputs(), DELTA);
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

}
