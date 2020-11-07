import java.util.Random;
import java.util.Scanner;

public class SpeedCalc extends Assessment {

	SpeedCalc(int rounds) {
		super(rounds);
	}
	
	/**
	 * Prints the random generated "Sum Array" and measures the time the user needs to calculate the sum,
	 * then saves the answer and the time of this round
	 */
	public void nextRound(Scanner input, int difficulty) {
		int[] arraySum = generateSumArray(difficulty);
		
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
	 * @param difficulty (1 = easy; 2 = advanced)
	 * @return
	 */
	private int[] generateSumArray(int difficulty) {
		difficulty = difficulty == 1 ? 5 : 7;
		int[] sumArray = new int[difficulty+1];
		
		Random rand = new Random();
		int sum = 0;
		for(int i = 0; i <= difficulty; ++i) {
			if(i == difficulty) {
				sumArray[i] = sum;
				break;
			} 
			int curNum = Math.abs(rand.nextInt() % 10);
			sumArray[i] = curNum;
			sum += curNum;
		}
		return sumArray;
	}

	
	public String toString() {
		return "Speed-Caclulation";
		
	}

	
}
