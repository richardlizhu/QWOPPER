public class QLearning {
	
	private double epsilon = 0.0;
	private double epsilonInc = 0.001;
	private MDPTable mdp;
	private boolean gameEnd = false; // indicate gameEnd
	
	public void Learn() {
		mdp = new MDPTable();
		while(epsilon <= 1) {
			while(!gameEnd) {
				move();
			}
			epsilon += epsilonInc;
		}
	}
	
	public void move() {
		double r = Math.random();
		if (r < epsilon) {
			// exploit
			int maxVal = 0;
			int maxAction = 0;
			for (int i = 0; i < 15; i++) {
				if (mdp.current.values[i] > maxVal) {
					maxVal = mdp.current.values[i];
					maxAction = i;
				}
			}
			mdp.takeAction(maxAction);
		} else {
			// explore
			int i = (int)(Math.random() * 16);
			mdp.takeAction(i);
		}
	}
	
}
