import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RememberNums extends Assessment {
	
	int[] correctCount;
	
	RememberNums(int rounds) {
		super(rounds);
		correctCount = new int[rounds];
	}
	
	/**
	 * Prints the random generated "Numbers Array" and after a 5sec timeout, checks if user input equals to the numbers shown 
	 * then saves the answer and the number of correctly remembered numbers.
	 */
	public void nextRound(Scanner input, int difficulty) {
		int[] numArray = generateNumArray(difficulty);
		
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
	 * generates an array with 3 or 5 (depending on difficulty) 3-digit numbers
	 * 
	 * @param difficulty (1 = easy; 2 = advanced)
	 * @return 
	 */
	private int[] generateNumArray(int difficulty) {
		difficulty = difficulty == 1 ? 3 : 5;
		
		Random rand = new Random();
		int[] numArray = new int[difficulty];
		for(int i = 0; i < difficulty; ++i) {
			numArray[i] = rand.nextInt(1000 - 100) + 100; 
		}
		return numArray;
	}
	
	
	private static void clearScreen() {   
		try {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows")) {
	        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        }
	        else {
	        	System.out.print("\033[H\033[2J");   
	    	    System.out.flush(); 
	        }
	    } catch (final Exception e) {
	    	
	    }  
	 }
	
	public String toString() {
		return "Remember Numbers";
		
	}
}
