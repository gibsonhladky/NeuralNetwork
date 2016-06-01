
/*
 * WeightGenerator is responsible for creating the weights
 * between Perceptrons in a Neural Network. The weights are
 * expected to be small, random numbers in approximately
 * the range [-1.0, 1.0]. Other ranges are possible, but
 * will vary in effectiveness. A generator may be implemented
 * to return non-random values, which would be useful in
 * testing or other reproducible environments.
 */
public interface WeightGenerator {

	public double nextWeight();
}
