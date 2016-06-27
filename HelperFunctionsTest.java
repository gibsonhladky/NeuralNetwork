import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utilities.HelperFunctions;

public class HelperFunctionsTest {

	@Test
	public void indexOfMaxReturnsCorrectIndex0()
	{
		double[] testArray = {1.0, 0.9, 0};
		int expectedIndex = 0;
		assertEquals(expectedIndex, HelperFunctions.indexOfMax(testArray));
	}
	
	@Test
	public void indexOfMaxReturnsCorrectIndexLastIndex()
	{
		double[] testArray = {0, 0.9, 1.0};
		int expectedIndex = 2;
		assertEquals(expectedIndex, HelperFunctions.indexOfMax(testArray));
	}
	
	@Test
	public void indexOfMaxWithNegativesLastIndex()
	{
		double[] testArray = {-1.0, -0.9, -0.8};
		int expectedIndex = 2;
		assertEquals(expectedIndex, HelperFunctions.indexOfMax(testArray));
	}
	
	@Test
	public void indexOfMaxWithNegativesFirstIndex()
	{
		double[] testArray = {-0.5, -0.9, -0.8};
		int expectedIndex = 0;
		assertEquals(expectedIndex, HelperFunctions.indexOfMax(testArray));
	}

}
