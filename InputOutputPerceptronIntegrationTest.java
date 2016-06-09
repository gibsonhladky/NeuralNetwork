import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.InputPerceptron;
import neuralNetwork.OutputPerceptron;
import neuralNetwork.WeightGenerator;

public class InputOutputPerceptronIntegrationTest {

	private final double DELTA = 0.001;
	private final WeightGenerator wg = new AlternatingMockWeightGenerator();
	
	private InputPerceptron input1;
	private InputPerceptron input2;
	private OutputPerceptron output1;
	private OutputPerceptron output2;
	
	@Before
	public void setUp() 
	{
		input1 = new InputPerceptron();
		input1.setInput(0.7);
		
		input2 = new InputPerceptron();
		input2.setInput(0.3);
		
		output1 = new OutputPerceptron(wg);
		output1.addInput(input1);
		output1.addInput(input2);
		output1.setExpectedOutput(1.0);
		
		output2 = new OutputPerceptron(wg);
		output2.addInput(input1);
		output2.addInput(input2);
		output2.setExpectedOutput(0.0);
		
		activateAllInOrder();
		calculateAllErrorsInOrder();
	}
	
	private void activateAllInOrder()
	{
		input1.activate();
		input2.activate();
		output1.activate();
		output2.activate();
	}
	
	private void calculateAllErrorsInOrder()
	{
		output1.calculateError();
		output2.calculateError();
		input1.calculateError();
		input2.calculateError();
	}
	
	private void adjustAllToError()
	{
		input1.adjustToError();
		input2.adjustToError();
		output1.adjustToError();
		output2.adjustToError();
	}
	
	private void adjustOutputsToError()
	{
		output1.adjustToError();
		output2.adjustToError();
	}

	@Test
	public void activationsPropagateCorrectOutputs()
	{
		final double expectedOutput1 = 0.645656;
		final double expectedOutput2 = 0.354344;
		assertEquals(expectedOutput1, output1.output(), DELTA);
		assertEquals(expectedOutput2, output2.output(), DELTA);
	}
	
	@Test
	public void errorCalculationsProduceCorrectError()
	{
		final double expectedError1 = 0.354344;
		final double expectedError2 = -0.354344;
		assertEquals(expectedError1, output1.error(), DELTA);
		assertEquals(expectedError2, output2.error(), DELTA);
	}
	
	@Test
	public void adjustToErrorChangesOutputsToCorrectNewValues()
	{
		adjustAllToError();
		activateAllInOrder();
		final double expectedOutput1 = 0.656053;
		final double expectedOutput2 = 0.348620;
		
		assertEquals(expectedOutput1, output1.output(), DELTA);
		assertEquals(expectedOutput2, output2.output(), DELTA);
	}
	
	@Test
	public void inputsDoNotNeedToAdjustToError()
	{
		adjustOutputsToError();
		activateAllInOrder();
		final double expectedOutput1 = 0.656053;
		final double expectedOutput2 = 0.348620;
		
		assertEquals(expectedOutput1, output1.output(), DELTA);
		assertEquals(expectedOutput2, output2.output(), DELTA);
	}

}
