package vending;

public class Bucket<T1, T2> {
	private T1 first;
	private T2 second;
	public Bucket(T1 first, T2 second)
	{ 
		this.first = first; 
		this.second = second;
		} 
	public T1 getFirst()
	{
		return first;
		} 
	public T2 getSecond()
	{
		return second;
		}


}
