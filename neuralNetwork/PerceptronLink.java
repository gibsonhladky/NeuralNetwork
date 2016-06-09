package neuralNetwork;

public class PerceptronLink {

	private Perceptron from;
	private Perceptron to;
	private double weight;
	
	public PerceptronLink(Perceptron from, Perceptron to, double initialWeight)
	{
		if(from == null || to == null)
		{
			throw new IllegalArgumentException("Cannot create a link with a null Perceptron.");
		}
		this.from = from;
		this.to = to;
		weight = initialWeight;
	}
	
	public void updateWeightBy(double amount)
	{
		weight += amount;
	}
	
	public Perceptron from()
	{
		return from;
	}
	
	public Perceptron to()
	{
		return to;
	}
	
	public double weightedOutput()
	{
		return weight * from.output();
	}
	
	public double weightedError()
	{
		return weight * to.error();
	}
	
}
