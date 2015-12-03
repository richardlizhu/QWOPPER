import java.io.FileNotFoundException;

public class QLearning {
	
	private static double epsilon = 0.0;
	private static double epsilonInc = 0.005;
	private static MDPTable mdp;
	private static double gamma = 0.95;
	private static boolean gameEnd = false; // indicate gameEnd
	private static double reward = 10;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Learn();
	}
	
	public static void Learn() throws FileNotFoundException {
		mdp = new MDPTable();
		while(epsilon <= 1) {
			while(!gameEnd) {
				move();
			}
			epsilon += epsilonInc;
			Meta.save("Data.txt", mdp.table);
			mdp.restart();
		}
	}
	
	public static void move() {
		double r = Math.random();
		if (r < epsilon) {
			// exploit
			double maxVal = 0;
			int maxAction = 0;
			for (int i = 0; i < Constants.NumActions; i++) {
				if (mdp.current.values[i] > maxVal) {
					maxVal = mdp.current.values[i];
					maxAction = i;
				}
			}
			TableEntry current = mdp.current;
			mdp.takeAction(maxAction); 
			current.values[maxAction] = reward + gamma*mdp.current.value();
		} else {
			// explore
			int i = (int)(Math.random() * Constants.NumActions);
			TableEntry current = mdp.current;
			mdp.takeAction(i); 
			current.values[i] = reward + gamma*mdp.current.value();
		}
	}
	
}
