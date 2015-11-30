import java.util.*;

public class MDP
{
	public HashMap<String, Node> nodes;
	public Node current;
	private Node start;
	
	public MDP() 
	{
		current = new Node();
		start = current;
		nodes = new HashMap<String, Node>();
		nodes.put(current.toString(), current);
	}
	
	//return value is the node you're at
	public Node takeAction(QWOP qwop)
	{
		current = new Node(current, qwop);
		if(!nodes.containsKey(current.toString()))
		{
			nodes.put(current.toString(), current);
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
