import neuralNetwork.WeightGenerator;

/*
 * Returns nextWeight alternating between +1 and -1, starting with +1.
 */
public class AlternatingMockWeightGenerator implements WeightGenerator {

	private boolean toggle;
	
	@Override
	public double nextWeight() {
		return (toggle = !toggle) ? 1 : -1;
	}

}
