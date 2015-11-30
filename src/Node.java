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
