import java.util.Scanner;

public class Assessment {
	int round;
	int[]times; 
	boolean[] correct;
	
	Assessment(int rounds){
		this.round = 0;
		times = new int[rounds];
		correct = new boolean[rounds];
	}
	
	public void nextRound(Scanner input, int difficulty) {

	}
	
	
	/**
	 * calls nextRound with the given difficulty "rounds" times
	 */
	public void runRounds(int rounds, Scanner input, int difficulty) {
		
		for(int i = 0; i < rounds; ++i) {
			nextRound(input, difficulty);
		}
		
	}
}
