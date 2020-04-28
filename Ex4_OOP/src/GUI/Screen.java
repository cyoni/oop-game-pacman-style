package GUI;

import java.text.DecimalFormat;

public class Screen {

	public static final int HEIGHT = 550;
	public static final int WIDTH = 1200;
	private static String timeLeft;
	private static double totalShortestDistance;


	
	public static double getTotalShortestDistance() {
		return totalShortestDistance;
	}
	
	public static void setTimeLeft(int number) {
		timeLeft = "Time left: " + number;
	}
	
	public static String getTimeLeft() {
		return timeLeft;
	}

	public static String getScreenLabel() {
 		return "shortest distance: " + totalShortestDistance;
	}

	public static void sumDistance(double distance) {
		totalShortestDistance+=distance;
		totalShortestDistance = Double.parseDouble(new DecimalFormat("##.#").format(totalShortestDistance));
	}
	
	public static void resetDistance() {
		totalShortestDistance = 0;
	}	
	
	
	
}
