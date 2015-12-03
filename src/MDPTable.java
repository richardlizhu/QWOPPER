import java.util.*;

public class MDPTable
{
	public HashMap<String, TableEntry> table;
	public TableEntry current;
	
	private TableEntry start;
	private QWOP[] qwops;
	
	public MDPTable()
	{
		current = new TableEntry(new Node());
		start = current;
		table = new HashMap<String, TableEntry>();
		table.put(current.toString(), current);
		
		qwops = new QWOP[Constants.NumActions];
		qwops[0] = new QWOP(false, false, false, false);
		qwops[1] = new QWOP(false, false, false, true);
		qwops[2] = new QWOP(false, false, true, false);
		qwops[3] = new QWOP(false, true, false, false);
		qwops[4] = new QWOP(false, true, false, true);
		qwops[5] = new QWOP(false, true, true, false);
		qwops[6] = new QWOP(true, false, false, false);
		qwops[7] = new QWOP(true, false, false, true);
		qwops[8] = new QWOP(true, false, true, false);
	}
	
	//return value is the new TableEntry you're at
	public TableEntry takeAction(int a)
	{
		if(a < 0 || a >= Constants.NumActions)
		{
			System.out.println("You have requested and invalid action.");
			return null;
		}
		
		QWOP pressed = qwops[a];
		
		//updateKeys(pressed);
		
		current = new TableEntry(new Node(current.node, pressed));
		if(!table.containsKey(current.toString()))
		{
			table.put(current.toString(), current);
		}
		
		return current;
	}
	
	public void restart()
	{
		current = start;
	}
	
	public int currentReward()
	{
		int reward = 9; //Call reward function
		return reward;
	}
}
