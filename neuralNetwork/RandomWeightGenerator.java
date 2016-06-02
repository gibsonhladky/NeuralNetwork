package neuralNetwork;
import java.util.Random;

/*
 * A weight generator that creates weights randomly in
 * the range (-1.0, 1.0). Can be instantiated with a seed
 * for reproducible conditions.
 */
public class RandomWeightGenerator implements WeightGenerator {

	Random rndm;
	
	public RandomWeightGenerator()
	{
		rndm = new Random();
	}
	
	public RandomWeightGenerator(int seed)
	{
		rndm = new Random(seed);
	}
	
	@Override
	public double nextWeight() {
		return rndm.nextBoolean() ? rndm.nextDouble() : -rndm.nextDouble();
	}
	
}
