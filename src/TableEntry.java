public class TableEntry 
{
	public Node node;
	public int[] values;
	
	public TableEntry(Node n)
	{
		this.node = n;
		values = new int[16];
		for(int i = 0; i < 16; i++)
		{
			values[i] = 0;
		}
	}
	
	public String toString()
	{
		return node.toString();
	}
}