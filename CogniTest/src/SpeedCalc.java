import java.util.Random;
import java.util.Scanner;

public class SpeedCalc extends Assessment {

	SpeedCalc(int rounds, int difficulty, String blockPrefix) {
		super(rounds, difficulty, blockPrefix);
	}
	
	/**
	 * Prints the random generated "Sum Array" and measures the time the user needs to calculate the sum,
	 * then saves the answer and the time of this round
	 */
	public void nextTask(Scanner input) {
		int[] arraySum = generateSumArray();
		
		for(int i = 0; i < arraySum.length-1; ++i) {
			if(i == arraySum.length-2) {
				System.out.print(arraySum[i] + " = ");
			} else {
				
				System.out.print(arraySum[i] + " + ");
			}
			
		}
		
		int startTime = (int) System.currentTimeMillis();
		int answer = input.nextInt();
		int endTime = (int) System.currentTimeMillis();

		correct[round] = (answer == arraySum[arraySum.length-1]);
		times[round] = endTime - startTime;
		round++;
	}
	
	/**
	 * generates array of 1 digit numbers (length depends on difficulty)
	 * the sum of all elements is in the last element
	 * 
	 * @difficulty (1 = easy; 2 = advanced)
	 */
	private int[] generateSumArray() {
		int size = difficulty == 1 ? 5 : 7;
		int[] sumArray = new int[size+1];
		
		Random rand = new Random();
		int sum = 0;
		for(int i = 0; i <= size; ++i) {
			int curNum = Math.abs(rand.nextInt() % 10);
			sumArray[i] = curNum;
			sum += curNum;
		}
		
		sumArray[size] = sum;
		return sumArray;
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
		return "Speed-Caclulation";
		
	}

	
}
