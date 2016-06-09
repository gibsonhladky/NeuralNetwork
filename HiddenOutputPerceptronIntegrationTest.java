import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.OutputPerceptron;
import neuralNetwork.PerceptronLink;
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
		
		PerceptronLink link1a = new PerceptronLink(hidden1, mockP, -1);
		PerceptronLink link1b = new PerceptronLink(hidden1, mockP, 1);
		PerceptronLink link2a = new PerceptronLink(hidden2, mockP, 1);
		PerceptronLink link2b = new PerceptronLink(hidden2, mockP, -1);
		
		hidden1.addOutputLink(link1a);
		hidden1.addOutputLink(link1b);
		hidden2.addOutputLink(link2a);
		hidden2.addOutputLink(link2b);
		
		activateAllInOrder();
	}

	@Test
	public void activationsPropagateCorrectOutputs() {
		// TODO: Use different weights
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
