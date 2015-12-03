import java.awt.Robot;
import java.io.FileNotFoundException;
import java.lang.System;

public class QLearning {
	
	private static double epsilon = 0.0;
	private static double epsilonInc = 0.005;
	private static MDPTable mdp;
	private static double gamma = 0.95;
	private static boolean gameEnd = false; // indicate gameEnd
	private static long start;
	private static long currentTime;
	public static Robot r;
	
	public static void main(String[] args) throws Exception
	{
		r = new Robot();
		Thread.sleep(1000);
		Learn();
	}
	
	public static void Learn() throws NumberFormatException, Exception {
		mdp = new MDPTable();
		while(epsilon <= 1) {
			start = System.currentTimeMillis();
			currentTime = start;
			while(!gameEnd) {
				long nextTimeStep = currentTime + (long)(Constants.timeStep * 1000);
				move();
				long temp = System.currentTimeMillis();
				if (temp < nextTimeStep)
					Thread.sleep(nextTimeStep-temp);
				currentTime = System.currentTimeMillis();
			}
			epsilon += epsilonInc;
			Meta.save("Data.txt", mdp.table);
			mdp.restart();
		}
	}
	
	public static void move() throws NumberFormatException, Exception {
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
			long time = System.currentTimeMillis() - start;
			current.values[maxAction] = Reward.reward(OCR.read(), time/1000.0) + gamma*mdp.current.value();
		} else {
			// explore
			int i = (int)(Math.random() * Constants.NumActions);
			TableEntry current = mdp.current;
			mdp.takeAction(i);
			long time = System.currentTimeMillis() - start;
			current.values[i] = Reward.reward(OCR.read(), time/1000.0) + gamma*mdp.current.value();
		}
	}
	
}
