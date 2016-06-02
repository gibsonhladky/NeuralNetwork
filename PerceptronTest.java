import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import neuralNetwork.Perceptron;

public class PerceptronTest {

	private final double DELTA = 0.001;
	
	@Test
	public void constructorNullInputs()
	{
		int testIndex = 0;
		Perceptron testP = new Perceptron(testIndex, null);
		
		assertEquals(testIndex, testP.index);
		assertTrue(testP.bias <= 1.0 && testP.bias >= -1.0);
		assertEquals(null, testP.inputs);
	}
	
	@Test
	public void constructorNonNullInputs()
	{
		int testIndex = 0;
		ArrayList<Perceptron> inputs = new ArrayList<Perceptron>(1);
		inputs.add(new Perceptron(0, null));
		
		Perceptron testP = new Perceptron(testIndex, inputs);
		
		assertEquals(testIndex, testP.index);
		assertTrue(testP.bias <= 1.0 && testP.bias >= -1.0);
		assertSame(inputs, testP.inputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorInvalidIndex()
	{
		int invalidIndex = -1;
		@SuppressWarnings("unused")
		Perceptron testP = new Perceptron(invalidIndex, null);
	}
	
	
	@Test
	public void activateInputLayerCalculation()
	{
		double[] inputs = {1.0};
		Perceptron p = new Perceptron(0, null);
		p.bias = -1;
		
		p.activate(inputs);
		
		assertEquals(0.5, p.activationValue, DELTA);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void activateInputLayerTooSmallInputs()
	{
		double[] inputs = {1.0};
		Perceptron p = new Perceptron(10, null);
		
		p.activate(inputs);
	}
	
	@Test (expected = IllegalStateException.class)
	public void activateHiddenLayerCallingInputLayerMethod()
	{
		Perceptron inputP = new Perceptron(0, null);
		ArrayList<Perceptron> inputLayer = new ArrayList<Perceptron>(1);
		
		inputLayer.add(inputP);
		
		Perceptron testP = new Perceptron(0, inputLayer);
		
		double[] inputs = {1.0};
		testP.activate(inputs);
	}
	
	@Test
	public void activateHiddenLayerCalculation()
	{
		Perceptron inputPerceptron1 = new Perceptron(0, null);
		inputPerceptron1.activationValue = -0.5;
		Perceptron inputPerceptron2 = new Perceptron(1, null);
		inputPerceptron2.activationValue = 0.5;
		
		ArrayList<Perceptron> inputLayer = new ArrayList<Perceptron>(2);
		inputLayer.add(inputPerceptron1);
		inputLayer.add(inputPerceptron2);
		
		Perceptron testPerceptron = new Perceptron(0, inputLayer);
		testPerceptron.bias = 0.0;
		testPerceptron.inputWeights.set(0, 1.0);
		testPerceptron.inputWeights.set(1, 1.0);
		testPerceptron.activate();
		
		assertEquals(0.5, testPerceptron.activationValue, DELTA);
		
	}
	
	@Test (expected = IllegalStateException.class)
	public void activateInputLayerCallingHiddenLayerMethod()
	{
		Perceptron testP = new Perceptron(0, null);
		
		testP.activate();
	}
	
	@Test
	public void calculateDeltasOutputLayerCalculation()
	{
		Perceptron testP = new Perceptron(0, null);
		testP.activationValue = 0.5;
		double[] expectedOutputs = {1.0};
		testP.calculateDeltas(expectedOutputs);
		
		assertEquals(-0.25, testP.delta, DELTA);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void calculateDeltasOutputLayerIndexOutOfBounds()
	{
		Perceptron testP = new Perceptron(10, null);
		double[] expectedOutputs = {1.0};
		
		testP.calculateDeltas(expectedOutputs);
	}
	
	@Test (expected = IllegalStateException.class)
	public void calculateDeltasOutputLayerCalledFromNonOutputLayer()
	{
		Perceptron outputP = new Perceptron(0, null);
		ArrayList<Perceptron> outputs = new ArrayList<Perceptron>(1);
		outputs.add(outputP);
		
		Perceptron testP = new Perceptron(0, null);
		testP.outputs = outputs;
		
		double[] expectedOutputs = {1.0};
		
		testP.calculateDeltas(expectedOutputs);
	}
	
	@Test
	public void calculateDeltasInnerLayerCalculation()
	{
		Perceptron testP = new Perceptron(0, null);
		testP.activationValue = 0.5;
		ArrayList<Perceptron> inputs = new ArrayList<Perceptron>(1);
		inputs.add(testP);
		
		Perceptron outputP1 = new Perceptron(0, inputs);
		outputP1.delta = 0.1;
		outputP1.inputWeights.set(0, 1.0);
		Perceptron outputP2 = new Perceptron(1, inputs);
		outputP2.delta = -0.1;
		outputP2.inputWeights.set(0, 0.5);
		ArrayList<Perceptron> outputs = new ArrayList<Perceptron>(2);
		outputs.add(outputP1);
		outputs.add(outputP2);
		
		testP.outputs = outputs;
		
		testP.calculateDeltas();
		
		assertEquals(0.0125, testP.delta, DELTA);
		
	}
	
	@Test (expected = IllegalStateException.class)
	public void calculateDeltasInnerLayerCalledFromOutputLayer()
	{
		Perceptron testP = new Perceptron(0, null);
		testP.calculateDeltas();
	}
	
	@Test
	public void updateWeightsCalculation()
	{
		Perceptron inputP = new Perceptron(0, null);
		ArrayList<Perceptron> inputs = new ArrayList<Perceptron>(1);
		inputs.add(inputP);
		
		Perceptron testP = new Perceptron(0, inputs);
		
		testP.delta = -0.1;
		testP.activationValue = 0.5;
		testP.inputWeights.set(0, 0.5);
		testP.bias = 0.1;
		
		testP.updateWeights();
		
		assertEquals(0.105, testP.bias, DELTA);
		assertEquals(0.505, testP.inputWeights.get(0), DELTA);
		
	}
}
