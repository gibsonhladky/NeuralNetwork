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
		testLayer.addPreviousLayer(new MockLayer());
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

}
