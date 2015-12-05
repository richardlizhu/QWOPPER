public class Node 
{
  public QWOP[] dna;
  
  public Node()
  {
	  dna = new QWOP[0];
  }
  
  public Node(QWOP qwop)
  {
	  dna = new QWOP[1];
	  dna[0] = qwop;
  }
  
  public Node(Node n)
  {
	  dna = new QWOP[n.dna.length];
	  for(int i = 0; i < n.dna.length; i++)
	  {
		  dna[i] = n.dna[i];
	  }
  }
  
  public Node(Node n, QWOP addition)
  {
	  dna = new QWOP[n.dna.length + 1];
	  for(int i = 0; i < n.dna.length; i++)
	  {
		  dna[i] = n.dna[i];
	  }
	  dna[n.dna.length] = addition;
  }
  
  public Node(String s)
  {
	  s = s.substring(1,s.length()-1);
	  dna = new QWOP[s.split("\\]\\[").length];
	  String[] qwops = s.split("\\]\\[");
	  for(int i = 0; i < qwops.length;i++)
	  {
		  boolean q = false;
		  boolean w = false;
		  boolean o = false;
		  boolean p = false;
		  String currentQWOP = qwops[i];
		  if (currentQWOP.charAt(0)=='1')
		  {
			  q = true;
		  }
		  if (currentQWOP.charAt(1)=='1')
		  {
			  w = true;
		  }
		  if (currentQWOP.charAt(2)=='1')
		  {
			  o = true;
		  }
		  if (currentQWOP.charAt(3)=='1')
		  {
			  p = true;
		  }
		  dna[i] = new QWOP(q,w,o,p);
	  }
  }
  
  /*
  public Node mutated(QWOP change)
  {  
	  Node n = new Node(this);
	  n.dna[n.dna.length - 1] = change;
	  return n;
  }
  */
  
  public String toString()
  {
	  String s = "";
	  for(int i = 0; i < dna.length; i++)
	  {
		  int q = (dna[i].q ? 1 : 0);
		  int w = (dna[i].w ? 1 : 0);
		  int o = (dna[i].o ? 1 : 0);
		  int p = (dna[i].p ? 1 : 0);
		  
		  s += (((("[" + q) + w) + o) + p) + "]";
	  }
	  
	  return s;
  }
}
