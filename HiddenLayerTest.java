

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenLayer;
import neuralNetwork.NetworkLayer;

public class HiddenLayerTest {

	HiddenLayer testLayer;
	
	@Before
	public void setUp()
	{
		testLayer = new HiddenLayer(2, new MockWeightGenerator());
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
		testLayer.addPreviousLayer(null);
	}
	
	@Test
	public void getPreviousLayersReturnsCorrectLayers()
	{
		MockLayer ml1 = new MockLayer(1);
		MockLayer ml2 = new MockLayer(1);
		List<NetworkLayer> expectedLayers = new ArrayList<NetworkLayer>(2);
		expectedLayers.add(ml1);
		expectedLayers.add(ml2);
		
		testLayer.addPreviousLayer(ml1);
		testLayer.addPreviousLayer(ml2);
		
		assertEquals(expectedLayers, testLayer.previousLayers());
	}

}
