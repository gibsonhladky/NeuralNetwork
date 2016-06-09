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
		hidden2 = new HiddenPerceptron(wg);
		output1 = new OutputPerceptron(wg);
		output2 = new OutputPerceptron(wg);
		
		PerceptronLink hidden1ToOutput1 = new PerceptronLink(hidden1, output1, -0.5);
		PerceptronLink hidden1ToOutput2 = new PerceptronLink(hidden1, output2, 0.5);
		PerceptronLink hidden2ToOutput1 = new PerceptronLink(hidden2, output1, 0.5);
		PerceptronLink hidden2ToOutput2 = new PerceptronLink(hidden2, output2, -0.7);
		
		hidden1.addOutputLink(hidden1ToOutput1);
		hidden1.addOutputLink(hidden1ToOutput2);
		hidden2.addOutputLink(hidden2ToOutput1);
		hidden2.addOutputLink(hidden2ToOutput2);
		
		output1.addInputLink(hidden1ToOutput1);
		output1.addInputLink(hidden1ToOutput2);
		output2.addInputLink(hidden2ToOutput1);
		output2.addInputLink(hidden2ToOutput2);
		
		PerceptronLink inLink1a = new PerceptronLink(hidden1, mockP, 0.7);
		PerceptronLink inLink1b = new PerceptronLink(hidden1, mockP, 0.3);
		PerceptronLink inLink2a = new PerceptronLink(hidden2, mockP, -0.5);
		PerceptronLink inLink2b = new PerceptronLink(hidden2, mockP, -0.3);
		
		hidden1.addOutputLink(inLink1a);
		hidden1.addOutputLink(inLink1b);
		hidden2.addOutputLink(inLink2a);
		hidden2.addOutputLink(inLink2b);
		
		activateAllInOrder();
	}

	@Test
	public void activationsPropagateCorrectOutputs() {
		final double expectedOutput1 = 0.6659938;
		final double expectedOutput2 = 0.242457;
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
