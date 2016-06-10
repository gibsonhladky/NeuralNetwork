package neuralNetwork;

public class HiddenLayer implements NetworkLayer {

	private HiddenPerceptron[] perceptrons;
	private final WeightGenerator weightGen;
	
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
		weightGen = wg;
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
	
	@Override
	public void appendTo(NetworkLayer prev)
	{
		if(prev == null)
		{
			throw new IllegalArgumentException("Cannot add a null previous network layer to a hidden layer.");
		}
		for(HiddenPerceptron hiddenP : perceptrons)
		{
			for(Perceptron inputP : prev.perceptrons())
			{
				PerceptronLink appendingLink = new PerceptronLink(inputP, hiddenP, weightGen.nextWeight());
				hiddenP.addInputLink(appendingLink);
				inputP.addInputLink(appendingLink);
			}
		}
	}

}
