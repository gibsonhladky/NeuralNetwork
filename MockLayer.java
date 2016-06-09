import neuralNetwork.NetworkLayer;
import neuralNetwork.Perceptron;

public class MockLayer implements NetworkLayer {

	private MockPerceptron[] perceptrons;
	
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

}
