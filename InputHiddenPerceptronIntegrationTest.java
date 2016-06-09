import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.InputPerceptron;
import neuralNetwork.PerceptronLink;
import neuralNetwork.WeightGenerator;

public class InputHiddenPerceptronIntegrationTest {

	private final double DELTA = 0.001;
	private final WeightGenerator wg = new AlternatingMockWeightGenerator();
	private final MockPerceptron mockP = new MockPerceptron();
	
	private InputPerceptron input1;
	private InputPerceptron input2;
	private HiddenPerceptron hidden1;
	private HiddenPerceptron hidden2;
	
	@Before
	public void setUp()
	{
		input1 = new InputPerceptron();
		input1.setInput(0.7);
		
		input2 = new InputPerceptron();
		input2.setInput(0.3);
		
		hidden1 = new HiddenPerceptron(wg);
		PerceptronLink inLink1a = new PerceptronLink(input1, hidden1, -1);
		PerceptronLink inLink1b = new PerceptronLink(input2, hidden1, 1);
		hidden1.addInputLink(inLink1a);
		hidden1.addInputLink(inLink1b);
		
		hidden2 = new HiddenPerceptron(wg);
		PerceptronLink inLink2a = new PerceptronLink(input1, hidden2, 1);
		PerceptronLink inLink2b = new PerceptronLink(input2, hidden2, -1);
		hidden2.addInputLink(inLink2a);
		hidden2.addInputLink(inLink2b);
		
		PerceptronLink outLink1a = new PerceptronLink(hidden1, mockP, 1);
		PerceptronLink outLink1b = new PerceptronLink(hidden1, mockP, 1);
		PerceptronLink outLink2a = new PerceptronLink(hidden2, mockP, -1);
		PerceptronLink outLink2b = new PerceptronLink(hidden2, mockP, -1);
		
		hidden1.addOutputLink(outLink1a);
		hidden1.addOutputLink(outLink1b);
		hidden2.addOutputLink(outLink2a);
		hidden2.addOutputLink(outLink2b);
		
		activateAllInOrder();
		calculateAllErrorsInOrder();
	}

	@Test
	public void ativationsPropagateCorrectOutputs()
	{
		final double expectedOutput1 = 0.645656;
		final double expectedOutput2 = 0.354344;
		assertEquals(expectedOutput1, hidden1.output(), DELTA);
		assertEquals(expectedOutput2, hidden2.output(), DELTA);
	}
	
	@Test
	public void errorCalculationsProduceCorrectError()
	{
		final double expectedError1 = 1.0;
		final double expectedError2 = -1.0;
		assertEquals(expectedError1, hidden1.error(), DELTA);
		assertEquals(expectedError2, hidden2.error(), DELTA);
	}
	
	@Test
	public void adjustToErrorAdjustsWeightsCorrectly()
	{
		adjustAllToErrors();
		activateAllInOrder();
		
		final double expectedOutput1 = 0.674615;
		final double expectedOutput2 = 0.338302;
		assertEquals(expectedOutput1, hidden1.output(), DELTA);
		assertEquals(expectedOutput2, hidden2.output(), DELTA);
	}
	
	private void activateAllInOrder()
	{
		input1.activate();
		input2.activate();
		hidden1.activate();
		hidden2.activate();
	}
	
	private void calculateAllErrorsInOrder()
	{
		hidden1.calculateError();
		hidden2.calculateError();
		input1.calculateError();
		input2.calculateError();
	}
	
	private void adjustAllToErrors()
	{
		input1.adjustToError();
		input2.adjustToError();
		hidden1.adjustToError();
		hidden2.adjustToError();
	}

}
