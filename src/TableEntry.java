public class TableEntry 
{
	public Node node;
	public double[] values;
	
	public TableEntry(Node n)
	{
		this.node = n;
		values = new double[Constants.NumActions];
		for(int i = 0; i < Constants.NumActions; i++)
		{
			values[i] = 0;
		}
	}
	
	public TableEntry(Node n, double[] v)
	{
		this.node = n;
		this.values = v;
	}
	
	public String toString()
	{
		return node.toString();
	}
	
	public double value()
	{
		double m = 0;
		for (int i = 0; i < 15; i++) {
			m = Math.max(m, values[i]);
		}
		return m;
	}
}
