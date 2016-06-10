import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.NeuralNetwork;
import neuralNetwork.WeightGenerator;

public class NeuralNetworkTests {

	private final WeightGenerator wg = new MockWeightGenerator();
	private final double DELTA = 0.001;
	
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
	public void trainHandlesNullOutputs()
	{
		final double[] inputs = {1, 1};
		testN.train(inputs, null);
	}
	
	@Test
	public void trainOnSingleInputGivesCorrectPrediction()
	{
		int[] layerSizes = {2, 2, 2};
		testN = new NeuralNetwork(layerSizes, wg);
		
		double[] trainingInputs = {1.0, 0.5};
		double[] trainingOutputs = {};
		testN.train(trainingInputs, trainingOutputs);

		double[] predictionInputs = {};
		double[] expectedPrediction = {};
//		assertArrayEquals(expectedPrediction, testN.predict(predictionInputs), DELTA);
	}

}
