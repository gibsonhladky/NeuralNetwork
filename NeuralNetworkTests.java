import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class NeuralNetworkTests {

	@Test
	public void constructor() {
		NeuralNetwork nn = new NeuralNetwork();
		
		assertNotNull(nn.hiddenLayers);
	}
	
	@Test
	public void addhiddenLayerstandard()
	{
		NeuralNetwork nn = new NeuralNetwork();
		nn.addLayer(100);
		
		assertEquals(1, nn.hiddenLayers.size());
		assertEquals(100, nn.hiddenLayers.get(0).size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addLayerInvalidSize()
	{
		NeuralNetwork nn = new NeuralNetwork();
		nn.addLayer(-1);
		assertEquals(0, nn.hiddenLayers.size());
		System.out.println("Invalid layer added");
	}
	
	@Test
	public void addLayerInputOnly()
	{
		NeuralNetwork nn = new NeuralNetwork();
		nn.addLayer(2);
		ArrayList<Perceptron> inputLayer = nn.hiddenLayers.get(0);
		for(Perceptron p : inputLayer)
		{
			assertNull(p.inputs);
			assertNull(p.outputs);
		}
	}
	
	@Test
	public void addLayerCorrectlyRewires()
	{
		NeuralNetwork nn = new NeuralNetwork();
		nn.addLayer(2);
		nn.addLayer(2);
		
		ArrayList<Perceptron> inputs = nn.hiddenLayers.get(0);
		ArrayList<Perceptron> outputs = nn.hiddenLayers.get(1);
		
		for(Perceptron p : inputs)
		{
			assertNull(p.inputs);
			assertEquals(outputs, p.outputs);
		}
		
		for(Perceptron p : outputs)
		{
			assertNull(p.outputs);
			assertEquals(inputs, p.inputs);
		}
		
	}
	
	

}
