import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.NeuralNetwork;
import neuralNetwork.WeightGenerator;

public class NeuralNetworkTests {

	private final WeightGenerator wg = new MockWeightGenerator();
	private final double DELTA = 0.0001;
	
	private NeuralNetwork testN;
	
	@Before
	public void setup()
	{
		int[] layerSizes = {2, 2, 2};
		testN = new NeuralNetwork(layerSizes, wg);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullLayerSizes()
	{
		testN = new NeuralNetwork(null, wg);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesSmallLayerSizes()
	{
		final int[] smallSizes = {1};
		testN = new NeuralNetwork(smallSizes, wg);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorHandlesNullWeightGenerator()
	{
		final int[] layerSizes = {1, 1};
		testN = new NeuralNetwork(layerSizes, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void trainHandlesNullInputs()
	{
		final double[] outputs = {1, 1};
		testN.train(null, outputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void trainHandlesSmallInputs()
	{
		final double[] inputs = {1};
		final double[] outputs = {1, 1};
		testN.train(inputs, outputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void trainHandlesLargeInputs()
	{
		final double[] inputs = {1, 1, 1};
		final double[] outputs = {1, 1};
		testN.train(inputs, outputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void trainHandlesNullOutputs()
	{
		final double[] inputs = {1, 1};
		testN.train(inputs, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void trainHandlesSmallOutputs()
	{
		final double[] inputs = {1, 1};
		final double[] outputs = {1};
		testN.train(inputs, outputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void trainHandlesLargeOutputs()
	{
		final double[] inputs = {1, 1};
		final double[] outputs = {1, 1, 1};
		testN.train(inputs, outputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void predictHandlesNullInputs()
	{
		testN.predict(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void predictHandlesSmallInputs()
	{
		final double[] inputs = {1};
		testN.predict(inputs);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void predictHandlesLargeInputs()
	{
		final double[] inputs = {1, 1, 1};
		testN.predict(inputs);
	}
	
	@Test
	public void untrainedPredictionGivesCorrectOutputs()
	{
		double[] predictInputs = {1, 0.5};
		double[] expectedOutputs = {0.94523, 0.94523};
		double[] actualOutputs = testN.networkOutputsFor(predictInputs);
		assertArrayEquals(expectedOutputs, actualOutputs, DELTA);
	}
	
	@Test
	public void trainOnSingleInputGivesCorrectPrediction()
	{
		int[] layerSizes = {2, 2, 2};
		testN = new NeuralNetwork(layerSizes, wg);
		
		double[] trainingInputs = {1.0, 0.5};
		double[] trainingOutputs = {1.0, 0};
		testN.train(trainingInputs, trainingOutputs);

		double[] predictionInputs = trainingInputs;
		int expectedPrediction = 0;
		assertEquals(expectedPrediction, testN.predict(predictionInputs));
	}

}
