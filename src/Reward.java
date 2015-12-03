
public class Reward {
	public static double reward(double distance, double time)
	{
		return (Constants.K1 / time + Constants.K2) * distance;
	}
	
	//A noble and complex function:
	public static double reward(double distance)
	{
		return distance;
	}
}
