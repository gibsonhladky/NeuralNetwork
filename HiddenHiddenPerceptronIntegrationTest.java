import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import neuralNetwork.HiddenPerceptron;
import neuralNetwork.PerceptronLink;
import neuralNetwork.WeightGenerator;

public class HiddenHiddenPerceptronIntegrationTest {

	private final MockPerceptron mockP = new MockPerceptron();
	private final WeightGenerator wg = new AlternatingMockWeightGenerator();
	private final double DELTA = 0.001;
	
	private HiddenPerceptron hidden1;
	private HiddenPerceptron hidden2;
	private HiddenPerceptron hidden3;
	private HiddenPerceptron hidden4;
	
	@Before
	public void setUp()
	{
		hidden1 = new HiddenPerceptron(wg);
		hidden2 = new HiddenPerceptron(wg);
		hidden3 = new HiddenPerceptron(wg);
		hidden4 = new HiddenPerceptron(wg);
		
		PerceptronLink mock1ToHidden1 = new PerceptronLink(mockP, hidden1, 0.7);
		PerceptronLink mock2ToHidden1 = new PerceptronLink(mockP, hidden1, 0.3);
		PerceptronLink mock1ToHidden2 = new PerceptronLink(mockP, hidden2, -0.5);
		PerceptronLink mock2ToHidden2 = new PerceptronLink(mockP, hidden2, -0.3);
		
		PerceptronLink hidden1ToHidden3 = new PerceptronLink(hidden1, hidden3, -0.5);
		PerceptronLink hidden2ToHidden3 = new PerceptronLink(hidden2, hidden3, 0.5);
		PerceptronLink hidden1ToHidden4 = new PerceptronLink(hidden1, hidden4, 0.6);
		PerceptronLink hidden2ToHidden4 = new PerceptronLink(hidden2, hidden4, -0.7);
		
		PerceptronLink hidden3ToMock3 = new PerceptronLink(hidden3, mockP, 0.3);
		PerceptronLink hidden3ToMock4 = new PerceptronLink(hidden3, mockP, 0.4);
		PerceptronLink hidden4ToMock3 = new PerceptronLink(hidden4, mockP, 0.5);
		PerceptronLink hidden4ToMock4 = new PerceptronLink(hidden4, mockP, 0.6);
		
		hidden1.addInputLink(mock1ToHidden1);
		hidden1.addInputLink(mock2ToHidden1);
		hidden2.addInputLink(mock1ToHidden2);
		hidden2.addInputLink(mock2ToHidden2);
		
		hidden1.addOutputLink(hidden1ToHidden3);
		hidden1.addOutputLink(hidden1ToHidden4);
		hidden2.addOutputLink(hidden2ToHidden3);
		hidden2.addOutputLink(hidden2ToHidden4);
		
		hidden3.addInputLink(hidden1ToHidden3);
		hidden3.addInputLink(hidden2ToHidden3);
		hidden4.addInputLink(hidden1ToHidden4);
		hidden4.addInputLink(hidden2ToHidden4);
		
		hidden3.addOutputLink(hidden3ToMock3);
		hidden3.addOutputLink(hidden3ToMock4);
		hidden4.addOutputLink(hidden4ToMock3);
		hidden4.addOutputLink(hidden4ToMock4);
		
		activateAllInOrder();
		calculateAllErrorsInOrder();
	}

	@Test
	public void activationsPropagateCorrectOutputs() {
		final double expectedH1Output = 0.8175745;
		final double expectedH2Output = 0.197816;
		assertEquals(expectedH1Output, hidden1.output(), DELTA);
		assertEquals(expectedH2Output, hidden2.output(), DELTA);
		
		final double expectedH3Output = 0.6659938;
		final double expectedH4Output = 0.342457;
		assertEquals(expectedH3Output, hidden3.output(), DELTA);
		assertEquals(expectedH4Output, hidden4.output(), DELTA);
	}
	
	@Test
	public void errorCalculationsProduceCorrectError()
	{
		final double expectedH3Error = 0.35;
		final double expectedH4Error = 0.55;
		assertEquals(expectedH3Error, hidden3.error(), DELTA);
		assertEquals(expectedH4Error, hidden4.error(), DELTA);
		
		final double expectedH1Error = 0.155;
		final double expectedH2Error = -0.21;
		assertEquals(expectedH1Error, hidden1.error(), DELTA);
		assertEquals(expectedH2Error, hidden2.error(), DELTA);
	}
	
	@Test
	public void adjustToErrorCorrectlyAdjustsWeights()
	{
		adjustAllToErrors();
		activateAllInOrder();
		final double expectedH1Output = 0.8213235;
		
		assertEquals(expectedH1Output, hidden1.output(), DELTA);
	}
	
	private void activateAllInOrder()
	{
		hidden1.activate();
		hidden2.activate();
		hidden3.activate();
		hidden4.activate();
	}
	
	private void calculateAllErrorsInOrder()
	{
		hidden4.calculateError();
		hidden3.calculateError();
		hidden2.calculateError();
		hidden1.calculateError();
	}
	
	private void adjustAllToErrors()
	{
		hidden1.adjustToError();
		hidden2.adjustToError();
		hidden3.adjustToError();
		hidden4.adjustToError();
	}

}
