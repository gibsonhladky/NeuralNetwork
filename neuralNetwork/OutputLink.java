package neuralNetwork;

import java.util.ArrayList;

import utilities.Pair;

public class OutputLink {

	ArrayList< Pair<Perceptron, Double>> links;
	
	public OutputLink()
	{
		links = new ArrayList< Pair<Perceptron, Double>>();
	}
	
	public double getError()
	{
		double error = 0;
		for(Pair<Perceptron, Double> link : links)
		{
			error += link.getLeft().error() * link.getRight();
		}
		return error;
	}
	
	public void addLink(Perceptron newOutput, Double weight)
	{
		links.add(new Pair<Perceptron, Double>(newOutput, weight));
	}
	
}
