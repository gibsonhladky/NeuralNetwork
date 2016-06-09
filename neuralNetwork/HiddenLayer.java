package neuralNetwork;

import java.util.List;

public class HiddenLayer implements NetworkLayer {

	private List<NetworkLayer> previousLayers;
	
	public HiddenLayer(int size, WeightGenerator wg)
	{
		if(size < 1)
		{
			throw new IllegalArgumentException("Cannot create a hidden layer with zero or negative size.");
		}
		if(wg == null)
		{
			throw new IllegalArgumentException("Cannot create a hidden layer with null weight generator.");
		}
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void calculateError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void adjustToError() {
		// TODO Auto-generated method stub

	}

	@Override
	public Perceptron[] perceptrons() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addPreviousLayer(NetworkLayer prev)
	{
		if(prev == null)
		{
			throw new IllegalArgumentException("Cannot add a null previous network layer to a hidden layer.");
		}
		previousLayers.add(prev);
	}
	
	public List<NetworkLayer> prevousLayers()
	{
		return previousLayers;
	}

}
