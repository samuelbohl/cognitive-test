import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		//SpeedCal Blocks
		SpeedCalc speedCalBlock1 = new SpeedCalc(16); // 2 * 8
		SpeedCalc speedCalBlock2 = new SpeedCalc(16); // 2 * 8
		
		//CountOnes Blocks
		CountOnes countOnesBlock1 = new CountOnes(18); // 3 * 6
		CountOnes countOnesBlock2 = new CountOnes(18); // 3 * 6
		
		//RememberNums Blocks
		RememberNums RememberNums1 = new RememberNums(16); // 2 * 8
		RememberNums RememberNums2 = new RememberNums(16); // 2 * 8
		
		//adds comment from commandline for the logs
		String comment = "";
		if(args.length > 0) {
			comment = args[0];
		}
		logStart(comment);
		
		
		//Test Routine
		
		clearScreen();
		System.out.println("Welcome to the CogniTest 1.0");
		
		System.out.println("First Block of Speed-Calculation: (Difficulty: 1) ");
		pause(2);
		speedCalBlock1.runRounds(8, input, 1);
		clearScreen();

		System.out.println("First Block of Speed-Calculation: (Difficulty: 2) ");
		pause(2);
		speedCalBlock1.runRounds(8, input, 2);
		clearScreen();
		
		logReport(speedCalBlock1, 8);
		
		System.out.println("First Block of Count Ones: (Difficulty: 1) ");
		pause(2);
		countOnesBlock1.runRounds(6, input, 1);
		clearScreen();
		
		System.out.println("First Block of Count Ones: (Difficulty: 2) ");
		pause(2);
		countOnesBlock1.runRounds(6, input, 2);
		clearScreen();
		
		System.out.println("First Block of Count Ones: (Difficulty: 3) ");
		pause(2);
		countOnesBlock1.runRounds(6, input, 3);
		clearScreen();
		
		logReport(countOnesBlock1, 6);
		
		System.out.println("First Block of Remember Numbers: (Difficulty: 1) ");
		pause(2);
		RememberNums1.runRounds(8, input, 1);
		clearScreen();
		
		System.out.println("First Block of Remember Numbers: (Difficulty: 1) ");
		pause(2);
		RememberNums1.runRounds(8, input, 2);
		clearScreen();
		
		logReport(RememberNums1, 8);
		
		// ROUND 2
		
		System.out.println("Seccond Block of Speed-Calculation: (Difficulty: 1) ");
		pause(2);
		speedCalBlock2.runRounds(8, input, 1);
		clearScreen();

		System.out.println("Seccond Block of Speed-Calculation: (Difficulty: 2) ");
		pause(2);
		speedCalBlock2.runRounds(8, input, 2);
		clearScreen();
		
		logReport(speedCalBlock2, 8);
		
		System.out.println("Seccond Block of Count Ones: (Difficulty: 1) ");
		pause(2);
		countOnesBlock2.runRounds(6, input, 1);
		clearScreen();
		
		System.out.println("Seccond Block of Count Ones: (Difficulty: 2) ");
		pause(2);
		countOnesBlock2.runRounds(6, input, 2);
		clearScreen();
		
		System.out.println("Seccond Block of Count Ones: (Difficulty: 3) ");
		pause(2);
		countOnesBlock2.runRounds(6, input, 3);
		clearScreen();
		
		logReport(countOnesBlock2, 6);
		
		System.out.println("Seccond Block of Remember Numbers: (Difficulty: 1) ");
		pause(2);
		RememberNums2.runRounds(8, input, 1);
		clearScreen();
		
		System.out.println("Seccond Block of Remember Numbers: (Difficulty: 2 ) ");
		pause(2);
		RememberNums2.runRounds(8, input, 2);
		clearScreen();
		
		logReport(RememberNums2, 8);
		
		logEnd();
		
		//END Routine
		
	}
	
	/**
	 * appends a new line in report.txt
	 */
	private static void logEnd() throws IOException {
		Path file = Path.of("./report.txt");
		
	    String previous = Files.readString(file);
		Files.writeString(file, previous+"\n \n");
		
	}
	
	/**
	 * logs the Assessment in report.txt
	 */
	public static void logReport(Assessment test, int blocksize) throws IOException {
		Path file = Path.of("./report.txt");
		String previous = Files.readString(file);
		
		String insertString = "";
		int timeSum = 0, cnt = 0;
		for(int i = 0; i < test.round; i++) {
			//if its the begining of a block size, the difficulty changes
			if(i%blocksize == 0) {
				insertString += test+": (Difficulty: "+ (i/blocksize +1) +") \n";
				timeSum = 0; cnt = 0;
			}
			//appending the values of the i-th task
			insertString += "("+test.correct[i]+ " " + test.times[i]+") ";
			
			//if the ith-task was correctly solved
			if(test.correct[i]) {
				cnt++;
				timeSum += test.times[i];
			}
			
			//if we reached the last task of this block and at least one task is solved correctly
			if(i%blocksize == blocksize-1 && cnt != 0) {
				double score = (double)cnt / timeSum * 10000;
				insertString +=   score + " \n";
			}
		}
		
		//inserting the insertString to report.txt
		Files.writeString(file, previous+insertString+"\n");
		
	}
	/**
	 * start of log - title, date and comments
	 */
	public static void logStart(String comment) throws IOException {
		Path file = Path.of("./report.txt");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
	    
	    String previous = Files.readString(file);
		Files.writeString(file, previous+"CogniTest 1.0 Log "+ formatter.format(date) + " Comment: "+comment + "\n");
	}
	
	/**
	 * pause for "time" seconds
	 */
	public static void pause(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clears commandline, depending on operating system
	 */
	public static void clearScreen() { 
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

}
