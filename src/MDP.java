import java.util.*;

public class MDP
{
	public HashMap<String, Node> nodes;
	public Node current;
	
	private Node start;
	private QWOP[] qwops;
	
	public MDP()
	{
		current = new Node();
		start = current;
		nodes = new HashMap<String, Node>();
		nodes.put(current.toString(), current);
		
		qwops = new QWOP[16];
		qwops[0] = new QWOP(false, false, false, false);
		qwops[1] = new QWOP(false, false, false, true);
		qwops[2] = new QWOP(false, false, true, false);
		qwops[3] = new QWOP(false, false, true, true);
		qwops[4] = new QWOP(false, true, false, false);
		qwops[5] = new QWOP(false, true, false, true);
		qwops[6] = new QWOP(false, true, true, false);
		qwops[7] = new QWOP(false, true, true, true);
		qwops[8] = new QWOP(true, false, false, false);
		qwops[9] = new QWOP(true, false, false, true);
		qwops[10] = new QWOP(true, false, true, false);
		qwops[11] = new QWOP(true, false, true, true);
		qwops[12] = new QWOP(true, true, false, false);
		qwops[13] = new QWOP(true, true, false, true);
		qwops[14] = new QWOP(true, true, true, false);
		qwops[15] = new QWOP(true, true, true, true);
	}
	
	//return value is the new Node you're at
	public Node takeAction(int a)
	{
		if(a < 0 || a >= qwops.length)
		{
			System.out.println("You have requested and invalid action.");
			return null;
		}
		
		QWOP pressed = qwops[a];
		
		//updateKeys(pressed);
		
		current = new Node(current, pressed);
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
