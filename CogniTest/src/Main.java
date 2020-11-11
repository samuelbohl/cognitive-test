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
		SpeedCalc speedCalBlock1Easy = new SpeedCalc(8, 1, "First");
		SpeedCalc speedCalBlock1Advanced = new SpeedCalc(8, 2, "First"); 
		
		SpeedCalc speedCalBlock2Easy = new SpeedCalc(8, 1, "Second");
		SpeedCalc speedCalBlock2Advanced = new SpeedCalc(8, 2, "Second"); 
		
		//CountOnes Blocks
		CountOnes countOnesBlock1Easy = new CountOnes(6, 1, "First");
		CountOnes countOnesBlock1Advanced = new CountOnes(6, 2, "First");
		CountOnes countOnesBlock1Hard = new CountOnes(6, 3, "First");
		
		CountOnes countOnesBlock2Easy = new CountOnes(6, 1, "Second");
		CountOnes countOnesBlock2Advanced = new CountOnes(6, 2, "Second");
		CountOnes countOnesBlock2Hard = new CountOnes(6, 3, "Second");
		
		//RememberNums Blocks
		RememberNums RememberNums1Easy = new RememberNums(8, 1, "First");
		RememberNums RememberNums1Advanced = new RememberNums(8, 2, "First");
		
		RememberNums RememberNums2Easy = new RememberNums(8, 1, "Second");
		RememberNums RememberNums2Advanced = new RememberNums(8, 2, "Second");
		
		//adds comment from commandline for the logs
		String comment = "";
		if(args.length > 0) {
			comment = args[0];
		}
		logStart(comment);
		
		
		//Test Routine
		
		System.out.println("Welcome to the CogniTest 1.0");
		
		speedCalBlock1Easy.runTasks(input);
		speedCalBlock1Advanced.runTasks(input);
		
		countOnesBlock1Easy.runTasks(input);
		countOnesBlock1Advanced.runTasks(input);
		countOnesBlock1Hard.runTasks(input);
		
		RememberNums1Easy.runTasks(input);
		RememberNums1Advanced.runTasks(input);
		
		// ROUND 2
		
		speedCalBlock2Easy.runTasks(input);
		speedCalBlock2Advanced.runTasks(input);
		
		countOnesBlock2Easy.runTasks(input);
		countOnesBlock2Advanced.runTasks(input);
		countOnesBlock2Hard.runTasks(input);
		
		RememberNums2Easy.runTasks(input);
		RememberNums2Advanced.runTasks(input);
		
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
	 * start of log - title, date and comments
	 */
	public static void logStart(String comment) throws IOException {
		Path file = Path.of("./report.txt");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
	    
	    String previous = Files.readString(file);
		Files.writeString(file, previous+"CogniTest 1.0 Log "+ formatter.format(date) + " Comment: "+comment + "\n");
	}

}
