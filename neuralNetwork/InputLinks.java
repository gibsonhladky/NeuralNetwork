package neuralNetwork;

import java.util.ArrayList;


public class InputLinks {
	
	private final double LEARNING_RATE = 0.1;
	
	private final WeightGenerator weightGen;
	private ArrayList<PerceptronLink> links;
	private Perceptron source;
	
	public InputLinks(Perceptron source, WeightGenerator wg)
	{
		if(source == null)
		{
			throw new IllegalArgumentException("Cannot create input links to a null source.");
		}
		if(wg == null)
		{
			throw new IllegalArgumentException("Cannot create input links with a null weight generator.");
		}
		
		weightGen = wg;
		this.source = source;
		links = new ArrayList<PerceptronLink>(1);
		addBias();
	}
	
	public double totalInput()
	{
		double inputValue = 0;
		for(PerceptronLink l : links)
		{
			inputValue += l.weightedOutput();
		}
		return inputValue;
	}
	
	public void adjustToErrorGivenOutput(double error, double output)
	{
		double updateAmount = error * output * LEARNING_RATE;
		for(PerceptronLink l : links)
		{
			l.updateWeightBy(updateAmount);
		}
	}
	
	public void addLink(PerceptronLink l)
	{
		if(l == null)
		{
			throw new IllegalArgumentException("Cannot add a null link to input links.");
		}
		links.add(l);
	}
	
	private void addBias()
	{
		PerceptronLink bias = new PerceptronLink(new BiasPerceptron(), source, weightGen.nextWeight());
		addLink(bias);
	}
	
}
