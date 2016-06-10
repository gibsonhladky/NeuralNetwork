import neuralNetwork.NetworkLayer;
import neuralNetwork.Perceptron;
import neuralNetwork.PerceptronLink;

public class MockLayer implements NetworkLayer {

	private MockPerceptron[] perceptrons;
	private final MockWeightGenerator wg = new MockWeightGenerator();
	
	public MockLayer(int size)
	{
		perceptrons = new MockPerceptron[size];
		for(int i = 0; i < size; i++)
		{
			perceptrons[i] = new MockPerceptron();
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
	public Perceptron[] perceptrons()
	{
		return perceptrons;
	}

	@Override
	public void appendTo(NetworkLayer l) {
		for(MockPerceptron mockP : perceptrons)
		{
			for(Perceptron inputP : l.perceptrons())
			{
				PerceptronLink appendingLink = new PerceptronLink(inputP, mockP, wg.nextWeight());
				inputP.addOutputLink(appendingLink);
				mockP.addInputLink(appendingLink);
			}
		}
	}

}
