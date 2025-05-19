package Scores;

import java.io.File;
import java.io.FileNotFoundException; 
import java.io.IOException;  
import java.util.List;
import java.util.Scanner; 
import java.io.FileWriter;   

public class Score {
	private int clicks;
	
	public Score(int clicks) {
		this.clicks = clicks;
	}
	
	public void scoreReplace(int diff) {
		try {
			File myObj = new File("src/Scores/scores.txt");
			String data = "";
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = data + myReader.nextLine() + "\n";
			}
			List<String> lines = data.lines().toList();
			String temp = "";
			boolean altered = false;
			FileWriter myWriter = new FileWriter("src/Scores/scores.txt");
	    	if (readScore(diff, lines) > clicks) {
	    	  	temp = lines.get(diff).substring(0, lines.get(diff).indexOf("=")+1);
	    	  	temp = temp + " " + clicks + "\n";
	    	  	altered = true;
	    	  	}
	    	if (altered) {
	    		for (int line = 0; line < lines.size(); line++) {
	    			if (line != diff) 
	    				myWriter.write(lines.get(line) + "\n");
	    			else
	    				myWriter.write(temp);
	    			}
		      	}
	    	if (!altered) {
	    		for (String line:lines) {
	    			myWriter.write(line + "\n");
	    			}	
	    		}
		    myReader.close();
		    myWriter.close();
		    } catch (FileNotFoundException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}

	private int readScore(int diff, List<String> lines) {
		return Integer.parseInt(lines.get(diff).substring(lines.get(diff).indexOf("=")+2));
	}
	  
	public void resetScores () {			//Realmente no los reinicia, los define en un valor poco razonable para el jugador promedio.
		try {
			File myObj = new File("src/Scores/scores.txt");
			String data = "";
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = data + myReader.nextLine() + "\n";
			}
			String temp = "";
			List<String> lines = data.lines().toList();
			FileWriter myWriter = new FileWriter("src/Scores/scores.txt");
			for (String line:lines) {
				temp = line.substring(0, line.indexOf("=")+1);
	    	  	temp = temp + " " + 60000 + "\n";
    			myWriter.write(temp);
    			}
			myReader.close();
		    myWriter.close();
		} catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	public int highScore (int diff) {
		int high = 0;
		try {
			File myObj = new File("src/Scores/scores.txt");
			String data = "";
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = data + myReader.nextLine() + "\n";
			}	
			List<String> lines = data.lines().toList();
			high = readScore(diff, lines);
			myReader.close();
			}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			}
		return high;
	 	}
}
