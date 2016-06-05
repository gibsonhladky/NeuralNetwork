package neuralNetwork;

import java.util.ArrayList;

public class OutputLink {

	private ArrayList<Perceptron> outputs;
	private ArrayList<Double> weights;
	private int size;
	
	public OutputLink()
	{
		outputs = new ArrayList<Perceptron>();
		weights = new ArrayList<Double>();
		size = 0;
	}
	
	public double getError()
	{
		double error = 0;
		for(int i = 0; i < size; i++)
		{
			error += outputs.get(i).error() * weights.get(i);
		}
		return error;
	}
	
	public void addLink(Perceptron newOutput, Double weight)
	{
		outputs.add(newOutput);
		weights.add(weight);
		size++;
	}
	
}
