import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RememberNums extends Assessment {
	
	int[] correctCount;
	
	RememberNums(int rounds, int difficulty, String blockPrefix) {
		super(rounds, difficulty, blockPrefix);
		correctCount = new int[rounds];
	}
	
	/**
	 * Prints the random generated "Numbers Array" and after a 5sec timeout, checks if user input equals to the numbers shown 
	 * then saves the answer and the number of correctly remembered numbers.
	 */

	public void nextTask(Scanner input) {
		int[] numArray = generateNumArray();
		
		for(int i = 0; i < numArray.length; ++i) {
			System.out.print(numArray[i] + " ");
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		clearScreen();
		
		int countCorrect = 0;
		for(int i = 0; i < numArray.length; ++i) {
			if(input.nextInt() == numArray[i]) countCorrect++;
		}
		
		correct[round] = (numArray.length == countCorrect);
		correctCount[round] = countCorrect;
		round++;

	}
	
	/**
	 * generates an array with 3 (or 5) (depending on difficulty) 3-digit (or 2-digit) numbers
	 * 
	 * @difficulty (1 = easy; 2 = advanced)
	 */
	private int[] generateNumArray() {
		int size  = difficulty == 1 ? 3 : 5;
		int min = difficulty == 1 ? 100 : 10;
		int max = difficulty == 1 ? 1000 : 100;
		
		Random rand = new Random();
		int[] numArray = new int[size];
		for(int i = 0; i < size; ++i) {
			
			numArray[i] = rand.nextInt(max - min) + min; 
		}
		return numArray;
	}
	
	/**
	 * calculates and returns the score
	 * Formula: (#number of memorized numbers) / (Sum of all displayd numbers, depending on difficulty) * 100
	 */
	public double getScore() {
		if(this.round < this.totalRounds) {
			return -1;
		}
		
		int totalCorrect = 0;
		for(int i = 0; i < correctCount.length; ++i) {
			totalCorrect += correctCount[i];
		}
		
		return (double)totalCorrect / (difficulty == 1 ? 3*3 : 3*5) * 100;
	}
	
	public String toString() {
		return "Remember Numbers";
		
	}
}
