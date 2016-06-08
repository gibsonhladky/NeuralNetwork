package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer implements NetworkLayer {

	/*
	 * Final layer of perceptrons in a neural network.
	 * These perceptrons will specifically handle unique
	 * calculations and feeding the output to the neural network.
	 */
	OutputPerceptron[] perceptrons;
	
	private List<NetworkLayer> previousLayer;
	
	double[] expectedOutputs;
	
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
		previousLayer = new ArrayList<NetworkLayer>();
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
		
	}
	
	public void setExpectedOutputs(double[] outputs)
	{
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
		return null;
	}
	
	public Perceptron[] perceptrons()
	{
		return null;
	}

}
