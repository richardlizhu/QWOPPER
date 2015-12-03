public class TableEntry 
{
	public Node node;
	public int[] values;
	
	public TableEntry(Node n)
	{
		this.node = n;
		values = new int[Constants.NumActions];
		for(int i = 0; i < Constants.NumActions; i++)
		{
			values[i] = 0;
		}
	}
	
	public TableEntry(Node n, int[] v)
	{
		this.node = n;
		this.values = v;
	}
	
	public String toString()
	{
		return node.toString();
	}
}
