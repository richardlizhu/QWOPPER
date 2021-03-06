import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Meta {
	public static void save(String location, HashMap<String, TableEntry> table) throws FileNotFoundException
	{
		File f = new File(location);
		PrintWriter writer = new PrintWriter(f);
		Set<String> keys = table.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			TableEntry current = table.get(key);
			writer.print(key);
			writer.print(";");
			writer.print(current.node.toString());
			writer.print(";");
			double[] currentValues = current.values;
			for(int i = 0;i < currentValues.length;i++)
			{
				writer.print(String.valueOf(currentValues[i]));
				writer.print(",");
			}
			writer.print(";");
			int[] currentVisits = current.visits;
			for(int i = 0; i < currentVisits.length; i++)
			{
				writer.print(String.valueOf(currentVisits[i]));
				writer.print(",");
			}
			writer.print(" ");
		}
		writer.close();
		/*
		for (int i = 0 ; i < size;i++)
		{
			Node currentNode = toSave[i];
			int n = currentNode.dna.length;
			for (int j = 0 ; j < n; j++)
			{
				QWOP currentQWOP = currentNode.dna[j];
				if (currentQWOP.q)
				{
					writer.print("q");
				}
				if (currentQWOP.w)
				{
					writer.print("w");
				}
				if (currentQWOP.o)
				{
					writer.print("o");
				}
				if (currentQWOP.p)
				{
					writer.print("p");
				}
				writer.print(",");
			}
			writer.print(" ");
		}
		*/
	}
	
	public static HashMap<String, TableEntry> load(String location) throws IOException
	{
		File f = new File(location);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = br.readLine();
		HashMap<String, TableEntry> table = new HashMap<String, TableEntry>();
		if (line != null)
		{
			String[] lines = line.split(" ");
			
			for(int i = 0; i < lines.length;i++)
			{
				String[] lineSplit = lines[i].split(";");
				String key = lineSplit[0];
				String node = lineSplit[1];
				String[] values = lineSplit[2].split(",");
				String[] visits = lineSplit[3].split(",");
				System.out.println(node);
				Node n = new Node(node);
				double[] vals = new double[Constants.NumActions];
				for (int j = 0; j < Constants.NumActions; j++)
				{
					System.out.println(values[j]);
					vals[j] = Double.valueOf(values[j]);
				}
				int[] vsts = new int[Constants.NumActions];
				for(int j = 0; j < Constants.NumActions; j++)
				{
					vsts[j] = Integer.valueOf(visits[j]);
				}
				TableEntry t = new TableEntry(n, vals, vsts);
				table.put(key, t);
			}
			br.close();
			
		}
		return table;
	}

}
