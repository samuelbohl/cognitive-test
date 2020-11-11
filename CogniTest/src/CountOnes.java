import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CountOnes extends Assessment {
	
	CountOnes(int rounds, int difficulty, String blockPrefix) {
		super(rounds, difficulty, blockPrefix);
	}
	
	/**
	 * Prints the random generated "Ones Array" and measures the time the user needs to calculate the number of ones,
	 * then saves the answer and the time of this round.
	 */
	public void nextTask(Scanner input) {
		String[] onesArray = generateOnesArray();
		
		for(int i = 0; i < onesArray.length; ++i) {
			System.out.print(onesArray[i] + " ");
		}
		
		int numOfOnes = countOnes(onesArray);
		int startTime = (int) System.currentTimeMillis();
		int answer = input.nextInt();
		int endTime = (int) System.currentTimeMillis();
		
		correct[round] = (answer == numOfOnes);
		times[round] = endTime - startTime;
		round++;
	}
	
	/**
	 * generates array with 1, i and l - length depends on difficulty
	 * 
	 * @param difficulty (1 = easy; 2 = advanced; 3 = hard)
	 * @return
	 */
	public String[] generateOnesArray() {
		int size = difficulty;
		if(difficulty == 1) size *= 10;
		else size = difficulty == 2 ? 15 : 20;
		
		Random rand = new Random();
		String[] OnesArray = new String[size];
		for(int i = 0; i < difficulty; ++i) {
			int nextRand = Math.abs(rand.nextInt()% 3);
			if(nextRand == 1) {
				OnesArray[i] = "1";
			} else {
				OnesArray[i] = nextRand == 2 ? "i" : "l";
			}
		}
		
		return  OnesArray;
	}
	
	/**
	 * counts number of 1s in the array
	 * 
	 * @param onesArray (String[] consisting of "1", "i" and "l")
	 * @return 
	 */
	private int countOnes(String[] onesArray) {
		int cnt = 0;
		for(int i = 0; i < onesArray.length; ++i) {
			if(onesArray[i].equals("1")) cnt++;
		}
		return cnt;
	}
	
	/**
	 * calculates and returns the score
	 * Formula: (#number of correctly solved tasks) / (Sum of time used to solve the correct tasks) * 10000
	 */
	public double getScore() {
		//if we did not finish this task
		if(this.round < this.totalRounds) {
			return -1;
		}
		
		//summing up all times of correctly solved tasks and number of correctly solved tasks
		int timeSum = 0, cnt = 0;
		for(int i = 0; i < correct.length; i++) {
			if(this.correct[i]) {
				cnt++;
				timeSum += this.times[i];
			}
		}
		return (double)cnt / timeSum * 10000;
	}
	
	
	public String toString() {
		return "Count Ones";
		
	}
	
}
