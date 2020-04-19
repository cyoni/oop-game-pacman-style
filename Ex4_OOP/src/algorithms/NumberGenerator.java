package algorithms;

public class NumberGenerator {

	

	public static int getRandomNumber(int Min, int Max) {
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}
	
}
