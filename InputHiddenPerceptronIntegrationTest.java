import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.InputPerceptron;
import neuralNetwork.WeightGenerator;

public class InputHiddenPerceptronIntegrationTest {

	private final double DELTA = 0.001;
	private final WeightGenerator wg = new AlternatingMockWeightGenerator();
	
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
		hidden1.addInputLink(input1);
		hidden1.addInputLink(input2);
		
		hidden2 = new HiddenPerceptron(wg);
		hidden2.addInputLink(input1);
		hidden2.addInputLink(input2);
		
		/*
		 *  Hidden perceptrons are wired to mock perceptrons
		 *  in this way to avoid creating 0 error due to
		 *  the alternating weight generator.
		 */
		hidden1.addOutputLink(new MockPerceptron());
		hidden2.addOutputLink(new MockPerceptron());
		hidden1.addOutputLink(new MockPerceptron());
		hidden2.addOutputLink(new MockPerceptron());
		
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
