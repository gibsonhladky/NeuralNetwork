package utilities;

public class Pair <E, F> {

	private E left;
	private F right;
	
	public Pair(E left, F right)
	{
		this.left = left;
		this.right = right;
	}
	
	public E getLeft()
	{
		return left;
	}
	
	public F getRight()
	{
		return right;
	}
	
	public void setLeft(E newLeft)
	{
		left = newLeft;
	}
	
	public void setRight(F newRight)
	{
		right = newRight;
	}
	
}
