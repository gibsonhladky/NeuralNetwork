import static org.junit.Assert.*;

import org.junit.Test;

public class NeuralNetworkTests {

	@Test
	public void constructor() {
		NeuralNetwork nn = new NeuralNetwork();
		
		assertNotNull(nn.layers);
	}

}
