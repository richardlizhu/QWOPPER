public class Node 
{
  private QWOP[] dna;
  
  public Node() {}
  
  public Node(QWOP qwop)
  {
	  dna = new QWOP[1];
	  dna[0] = qwop;
  }
  
  public String toString()
  {
	  String s = "";
	  for(int i = 0; i < dna.length; i++)
	  {
		  int q = (dna[i].q ? 1 : 0);
		  int w = (dna[i].w ? 1 : 0);
		  int o = (dna[i].o ? 1 : 0);
		  int p = (dna[i].p ? 1 : 0);
		  
		  s += "[" + q + w + o + p + "]";
	  }
	  
	  return s;
  }
}
