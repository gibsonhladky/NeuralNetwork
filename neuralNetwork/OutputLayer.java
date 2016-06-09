package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer implements NetworkLayer {

	/*
	 * Final layer of perceptrons in a neural network.
	 * These perceptrons will specifically handle unique
	 * calculations and feeding the output to the neural network.
	 */
	private OutputPerceptron[] perceptrons;
	
	private List<NetworkLayer> previousLayers;
	
	public OutputLayer(int size, WeightGenerator wg)
	{
		if(size < 1)
		{
			throw new IllegalArgumentException("OutputLayer must have positive size.");
		}
		if(wg == null)
		{
			throw new IllegalArgumentException("Cannot have a null weight generator.");
		}
		
		perceptrons = new OutputPerceptron[size];
		for(int i = 0; i < size; i++)
		{
			perceptrons[i] = new OutputPerceptron(wg);
		}
		previousLayers = new ArrayList<NetworkLayer>();
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
	public void adjustToError()
	{
		for(Perceptron p : perceptrons)
		{
			p.adjustToError();
		}
	}
	
	public void addPreviousLayer(NetworkLayer prev)
	{
		if(prev == null)
		{
			throw new IllegalArgumentException("Cannot add a null network layer.");
		}
		previousLayers.add(prev);
		Perceptron[] newInputs = prev.perceptrons();
		for(OutputPerceptron p : perceptrons)
		{
			for(Perceptron inputP : newInputs)
			{
				p.addInputLink(inputP);
			}
		}
	}
	
	public void setExpectedOutputs(double[] outputs)
	{
		if(outputs == null)
		{
			throw new IllegalArgumentException("Cannot provide null for expected outputs");
		}
		if(outputs.length != perceptrons.length){
			throw new IllegalArgumentException("Cannot provide expected outputs of different size than output layer");
		}
		
		for(int i = 0; i < perceptrons.length; i++)
		{
			perceptrons[i].setExpectedOutput(outputs[i]);
		}
	}
	
	/*
	 * Returns the output of each perceptron in this layer.
	 */
	public double[] getOutputs()
	{
		double[] outputs = new double[perceptrons.length];
		for(int i = 0; i < perceptrons.length; i++)
		{
			outputs[i] = perceptrons[i].output();
		}
		return outputs;
	}
	
	public Perceptron[] perceptrons()
	{
		return perceptrons;
	}
	
	public List<NetworkLayer> previousLayers()
	{
		return previousLayers;
	}

}
