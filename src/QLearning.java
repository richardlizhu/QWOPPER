public class QLearning {
	
	private double epsilon = 0.0;
	private double epsilonInc = 0.005;
	private MDPTable mdp;
	private double gamma = 0.95;
	private boolean gameEnd = false; // indicate gameEnd
	private double reward = 10;
	
	public void Learn() {
		mdp = new MDPTable();
		while(epsilon <= 1) {
			while(!gameEnd) {
				move();
			}
			epsilon += epsilonInc;
			mdp.restart();
		}
	}
	
	public void move() {
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
			String key = mdp.current.toString();
			mdp.takeAction(maxAction); 
			mdp.table.get(key).values[maxAction] = reward + gamma*mdp.current.value();
		} else {
			// explore
			int i = (int)(Math.random() * 16);
			mdp.takeAction(i);
		}
	}
	
}
