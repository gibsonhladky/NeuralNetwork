package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer implements NetworkLayer {

	private HiddenPerceptron[] perceptrons;
	private List<NetworkLayer> previousLayers;
	private List<NetworkLayer> nextLayers;
	
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
		
		perceptrons = new HiddenPerceptron[size];
		for(int i = 0; i < perceptrons.length; i++)
		{
			perceptrons[i] = new HiddenPerceptron(wg);
		}
		previousLayers = new ArrayList<NetworkLayer>();
		nextLayers = new ArrayList<NetworkLayer>();
	}
	
	@Override
	public void activate() {
		for(Perceptron p : perceptrons)
		{
			p.activate();
		}
	}

	@Override
	public void calculateError() {
		for(Perceptron p : perceptrons)
		{
			p.calculateError();
		}
	}

	@Override
	public void adjustToError() {
		for(Perceptron p : perceptrons)
		{
			p.adjustToError();
		}
	}

	@Override
	public Perceptron[] perceptrons() {
		return perceptrons;
	}
	
	public void addNextLayer(NetworkLayer next)
	{
		if(next == null)
		{
			throw new IllegalArgumentException("Cannot add a null previous network layer to a hidden layer.");
		}
		nextLayers.add(next);
		for(HiddenPerceptron hiddenP : perceptrons)
		{
			for(Perceptron outputP : next.perceptrons())
			{
				hiddenP.addOutputLink(outputP);
			}
		}
	}
	
	public List<NetworkLayer> nextLayers()
	{
		return nextLayers;
	}
	
	public void addPreviousLayer(NetworkLayer prev)
	{
		if(prev == null)
		{
			throw new IllegalArgumentException("Cannot add a null previous network layer to a hidden layer.");
		}
		previousLayers.add(prev);
		for(HiddenPerceptron hiddenP : perceptrons)
		{
			for(Perceptron inputP : prev.perceptrons())
			{
				hiddenP.addInputLink(inputP);
			}
		}
	}
	
	public List<NetworkLayer> previousLayers()
	{
		return previousLayers;
	}

}
