import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.lang.System;
import java.util.ArrayList;

public class QLearning {
	
	private static double epsilon = 0.0;
	private static ArrayList<Double> epsilons = new ArrayList<Double>();
	private static double epsilonInc = 0.05;
	private static MDPTable mdp;
	private static double gamma = 0.95;
	private static boolean gameEnd = false;
	private static long start;
	private static long currentTime;
	private static double record = -1000;
	private static long recordTime = 0; 
	public static Robot r;
	private static double allTimeRecord = -1000;
	private static int gameCounter = 0;
	
	public static void main(String[] args) throws Exception
	{
		r = new Robot();
		Thread.sleep(2000);
		Learn();
	}
	
	public static void Learn() throws NumberFormatException, Exception {
		mdp = new MDPTable();
		mdp.table = Meta.load("Data.txt");
		int level;
		while(true) {
			if (gameCounter > 10)
			{
				Meta.save("Data.txt", mdp.table);
				gameCounter = 0;
			}
			gameCounter++;
			start = System.currentTimeMillis();
			currentTime = start;
			level = 0;
			while(!gameEnd) {
				long nextTimeStep = currentTime + (long)(Constants.timeStep * 1000);
				move();
				long temp = System.currentTimeMillis();
				if (temp < nextTimeStep)
					Thread.sleep(nextTimeStep-temp);
				currentTime = System.currentTimeMillis();
				checkRecord();
				if(epsilons.size() == level)
					epsilons.add(epsilon);
				epsilons.set(level, Math.min(0.99, epsilons.get(level) + epsilonInc));
				level++;
			}
			//epsilon = Math.min(0.99, epsilon+epsilonInc);
			
			mdp.restart();
			System.out.println(String.valueOf(record));
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
			updateValue(maxAction);
		} else {
			// explore
			int i = (int)(Math.random() * Constants.NumActions);
			updateValue(i);
		}
	}
	
	private static void updateValue(int i) throws Exception {
		TableEntry current = mdp.current;
		mdp.takeAction(i);
		long time = System.currentTimeMillis() - start;
		double dist = OCR.read();
		current.visits[i]++;
		current.values[i] = (1.0 - 1.0/current.visits[i])*current.values[i] + (1.0/current.visits[i])*(Reward.reward(dist, time/1000.0) + gamma*mdp.current.value());
		if (dist > record) {
			record = dist;
			recordTime = System.currentTimeMillis();
		}
	}
	
	private static void checkRecord() {
		Color color = QLearning.r.getPixelColor(OCRConstants.yellowX, OCRConstants.yellowY);
		if (color.getRed() == 255 && color.getBlue() == 0 && color.getGreen() == 255)
		{
			gameEnd = true;
		}
		else
		{
			if (System.currentTimeMillis() - recordTime > 8000) {
				gameEnd = true;
			}
		}

	}
	
}
