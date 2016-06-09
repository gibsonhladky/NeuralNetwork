package neuralNetwork;

import java.util.ArrayList;

/*
 * OutputLink represents the outgoing edges from
 * perceptrons. It is used to keep track of each link
 * between perceptrons and to produce the error
 * associated with the perceptron from it's outputs.
 */
public class OutputLink {

	private Perceptron source;
	private ArrayList<PerceptronLink> links;
	
	public OutputLink(Perceptron source)
	{
		if(source == null)
		{
			throw new IllegalArgumentException("Cannot have a null source.");
		}
		this.source = source;
		links = new ArrayList<PerceptronLink>();
	}
	
	/*
	 * Returns the total error associated with these links.
	 */
	public double getTotalError()
	{
		double error = 0;
		for(PerceptronLink l : links)
		{
			error += l.weightedError();
		}
		return error;
	}
	
	/*
	 * Introduces a new link to the OutputLink.
	 */
	public void addLink(PerceptronLink newOutputLink)
	{
		if(newOutputLink == null)
		{
			throw new IllegalArgumentException("Cannot add a null link.");
		}
		if(newOutputLink.from() != source)
		{
			throw new IllegalArgumentException("Cannot add a link from a different source.");
		}
		links.add(newOutputLink);
	}
	
}
