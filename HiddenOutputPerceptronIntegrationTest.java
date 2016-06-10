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
		
		PerceptronLink mock1ToHidden1 = new PerceptronLink(mockP, hidden1, 0.7);
		PerceptronLink mock2ToHidden1 = new PerceptronLink(mockP, hidden1, 0.3);
		PerceptronLink mock1ToHidden2 = new PerceptronLink(mockP, hidden2, -0.5);
		PerceptronLink mock2ToHidden2 = new PerceptronLink(mockP, hidden2, -0.3);
		
		hidden1.addInputLink(mock1ToHidden1);
		hidden1.addInputLink(mock2ToHidden1);
		hidden2.addInputLink(mock1ToHidden2);
		hidden2.addInputLink(mock2ToHidden2);
		
		PerceptronLink hidden1ToOutput1 = new PerceptronLink(hidden1, output1, -0.5);
		PerceptronLink hidden1ToOutput2 = new PerceptronLink(hidden1, output2, 0.6);
		PerceptronLink hidden2ToOutput1 = new PerceptronLink(hidden2, output1, 0.5);
		PerceptronLink hidden2ToOutput2 = new PerceptronLink(hidden2, output2, -0.7);
		
		hidden1.addOutputLink(hidden1ToOutput1);
		hidden1.addOutputLink(hidden1ToOutput2);
		hidden2.addOutputLink(hidden2ToOutput1);
		hidden2.addOutputLink(hidden2ToOutput2);
		
		output1.addInputLink(hidden1ToOutput1);
		output1.addInputLink(hidden2ToOutput1);
		output2.addInputLink(hidden1ToOutput2);
		output2.addInputLink(hidden2ToOutput2);
		
		output1.setExpectedOutput(1);
		output2.setExpectedOutput(0);
		activateAllInOrder();
		calculateAllErrorsInOrder();
	}

	@Test
	public void activationsPropagateCorrectOutputs() {
		final double expectedH1Output = 0.8175745;
		final double expectedH2Output = 0.197816;
		assertEquals(expectedH1Output, hidden1.output(), DELTA);
		assertEquals(expectedH2Output, hidden2.output(), DELTA);
		
		final double expectedO1Output = 0.6659938;
		final double expectedO2Output = 0.342457;
		assertEquals(expectedO1Output, output1.output(), DELTA);
		assertEquals(expectedO2Output, output2.output(), DELTA);
	}
	
	@Test
	public void errorCalculationsProduceCorrectError()
	{
		final double expectedO1Error = 0.3340062;
		final double expectedO2Error = -0.343457;
		assertEquals(expectedO1Error, output1.error(), DELTA);
		assertEquals(expectedO2Error, output2.error(), DELTA);
		
		final double expectedH1Error = -0.3730773;
		final double expectedH2Error = 0.407423;
		assertEquals(expectedH1Error, hidden1.error(), DELTA);
		assertEquals(expectedH2Error, hidden2.error(), DELTA);
	}
	
	@Test
	public void adjustToErrorAdjustsWeightsCorrectly()
	{
		adjustAllToErrors();
		activateAllInOrder();
		
		final double expectedH1Output = 0.808299;
		final double expectedH2Output = 0.2003866;
		assertEquals(expectedH1Output, hidden1.output(), DELTA);
		assertEquals(expectedH2Output, hidden2.output(), DELTA);
		
		final double expectedO1Output = 0.677155;
		final double expectedO2Output = 0.336488;
		assertEquals(expectedO1Output, output1.output(), DELTA);
		assertEquals(expectedO2Output, output2.output(), DELTA);
	}
	
	private void activateAllInOrder()
	{
		hidden1.activate();
		hidden2.activate();
		output1.activate();
		output2.activate();
	}
	
	private void calculateAllErrorsInOrder()
	{
		output1.calculateError();
		output2.calculateError();
		hidden1.calculateError();
		hidden2.calculateError();
	}
	
	private void adjustAllToErrors()
	{
		hidden1.adjustToError();
		hidden2.adjustToError();
		output1.adjustToError();
		output2.adjustToError();
	}

}
