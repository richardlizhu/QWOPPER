import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.lang.System;

public class QLearning {
	
	private static double epsilon = 0.0;
	private static double epsilonInc = 0.001;
	private static MDPTable mdp;
	private static double gamma = 0.95;
	private static boolean gameEnd = false;
	private static long start;
	private static long currentTime;
	private static double record = -1000;
	private static long recordTime = 0; 
	public static Robot r;
	private static double allTimeRecord = -1000;
	
	public static void main(String[] args) throws Exception
	{
		r = new Robot();
		Thread.sleep(2000);
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
				checkRecord();
			}
			epsilon += epsilonInc;
			Meta.save("Data.txt", mdp.table);
			mdp.restart();
			if (record > allTimeRecord) {
				System.out.println("allTimeRecord: " + String.valueOf(record));
				allTimeRecord = record;
			}
			recordTime = 0;
			record = -1000;
			gameEnd = false;
			Bot.press(new QWOP());
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_R);
			Thread.sleep(500);
			r.keyRelease(KeyEvent.VK_R);
			r.keyRelease(KeyEvent.VK_CONTROL);
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
			if (maxVal == 0) {
				maxAction = (int)(Math.random() * Constants.NumActions);
			}
			TableEntry current = mdp.current;
			mdp.takeAction(maxAction); 
			long time = System.currentTimeMillis() - start;
			double dist = OCR.read();
			current.values[maxAction] = Reward.reward(dist, time/1000.0) + gamma*mdp.current.value();
			if (dist > record) {
				record = dist;
				recordTime = System.currentTimeMillis();
			}
		} else {
			// explore
			int i = (int)(Math.random() * Constants.NumActions);
			TableEntry current = mdp.current;
			mdp.takeAction(i);
			long time = System.currentTimeMillis() - start;
			double dist = OCR.read();
			current.values[i] = Reward.reward(dist, time/1000.0) + gamma*mdp.current.value();
			if (dist > record) {
				record = dist;
				recordTime = System.currentTimeMillis();
			}
		}
	}
	
	private static void checkRecord() {
		if (System.currentTimeMillis() - recordTime > 2000) {
			gameEnd = true;
		}
	}
	
}
