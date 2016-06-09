import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.OutputPerceptron;
import neuralNetwork.WeightGenerator;

public class HiddenOutputPerceptronIntegrationTest {

	private final double DELTA = 0.001;
	private final WeightGenerator wg = new AlternatingMockWeightGenerator();
	private final MockPerceptron mockP = new MockPerceptron();
	
	private HiddenPerceptron hidden1;
	private HiddenPerceptron hidden2;
	private OutputPerceptron output1;
	private OutputPerceptron output2;
	
	
	@Before
	public void setUp()
	{
		hidden1 = new HiddenPerceptron(wg);
		hidden1.addInputLink(mockP);
		hidden1.addInputLink(mockP);
		
		hidden2 = new HiddenPerceptron(wg);
		hidden2.addInputLink(mockP);
		hidden2.addInputLink(mockP);
		
		output1 = new OutputPerceptron(wg);
		output1.addInput(hidden1);
		output1.addInput(hidden2);
		
		output2 = new OutputPerceptron(wg);
		output2.addInput(hidden1);
		output2.addInput(hidden2);
		
		hidden1.addOutputLink(output1);
		hidden1.addOutputLink(output2);
		hidden2.addOutputLink(output1);
		hidden2.addOutputLink(output2);
		
		activateAllInOrder();
	}

	@Test
	public void activationsPropagateCorrectOutputs() {
		final double expectedOutput1 = 0.6313198;
		final double expectedOutput2 = 0.3686807;
		assertEquals(expectedOutput1, hidden1.output(), DELTA);
		assertEquals(expectedOutput2, hidden2.output(), DELTA);
	}
	
	private void activateAllInOrder()
	{
		hidden1.activate();
		hidden2.activate();
		output1.activate();
		output2.activate();
	}

}
