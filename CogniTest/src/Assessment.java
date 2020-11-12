import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public abstract class Assessment {
	int round;
	int totalRounds;
	int[]times; 
	boolean[] correct;
	int difficulty;
	String blockPrefix;
	
	Assessment(int rounds, int difficulty, String blockPrefix){
		this.round = 0;
		this.totalRounds = rounds;
		times = new int[rounds];
		correct = new boolean[rounds];
		this.difficulty = difficulty;
		this.blockPrefix = blockPrefix;
	}
	
	protected abstract void nextTask(Scanner input);
	
	
	/**
	 * Displays Title of this Taskblock and calls nextTask "rounds" times, after that the Console gets cleared
	 * 
	 * @param blockPrefix (= "First" or "Second")
	 */
	public void runTasks(Scanner input) {
		System.out.println(this.blockPrefix+" Block of "+this+": (Difficulty: "+this.difficulty+") ");
		pause(2);
		for(int i = 0; i < this.totalRounds; ++i) {
			nextTask(input);
		}
		clearScreen();
		log();
	}
	
	/**
	 * logs the Assessment in report.txt
	 */
	private void log() {
		Path file = Path.of("./report.txt");
		String previous = "";
		try {
			previous = Files.readString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String insertString = this+": (Difficulty: "+ this.difficulty +") \n";;
		for(int i = 0; i < totalRounds; i++) {
			//appending the values of the i-th task
			insertString += "("+this.correct[i]+ " " + this.times[i]+") ";
		}
		
		//inserting the insertString to report.txt
		try {
			Files.writeString(file, previous+insertString+"Score: "+getScore()+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * pause for "time" seconds
	 */
	private static void pause(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clears commandline, depending on operating system
	 */
	protected static void clearScreen() { 
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
		return "Assessment";
	}
	
	
	private double getScore() {
		return 0;
	}
}
