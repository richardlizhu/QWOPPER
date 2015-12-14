public class TableEntry 
{
	public Node node;
	public double[] values;
	public int[] visits;
	
	public TableEntry(Node n)
	{
		this.node = n;
		values = new double[Constants.NumActions];
		for(int i = 0; i < Constants.NumActions; i++)
		{
			values[i] = 0;
		}
		visits = new int[Constants.NumActions];
		for(int i = 0; i < Constants.NumActions; i++)
		{
			visits[i] = 0;
		}
	}
	
	public TableEntry(Node n, double[] v, int[] visits)
	{
		this.node = n;
		this.values = v;
		this.visits = visits;
	}
	
	public String toString()
	{
		return node.toString();
	}
	
	public double value()
	{
		double m = -99999999999.0;
		for (int i = 0; i < Constants.NumActions; i++) {
			m = Math.max(m, values[i]);
		}
		return m;
	}
}
