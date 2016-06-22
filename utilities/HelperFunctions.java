package utilities;

public class HelperFunctions {

	/*
	 * Returns the index of the maximum value in the array.
	 */
	public static int indexOfMax(double[] arr)
	{
		double max = arr[0];
		int index = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] > max)
			{
				index = i;
			}
		}
		return index;
	}
}
