package Scores;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreFileEditor {
	private String route;
	private int score;
	
	public ScoreFileEditor(String route, int score) {
		this.route = route;
		this.score = score;
	}

	public void replaceScore(int difficulty) {
		List<String> lines = readFile(route);
		if (lines == null || difficulty >= lines.size()) {
			return;
		}
		try {
			editFile(route, difficulty, lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int readScore(int difficulty) {
		try {
			List<String> lines = readFile(route);
			String currentScore = lines.get(difficulty);
			return Integer.parseInt(currentScore.substring(currentScore.indexOf("=") + 2));
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			e.printStackTrace();
			return 60000;
		}
	}

	private List<String> readFile(String route) {
		List<String> lines = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(route);
			 Scanner reader = new Scanner(fis)) {

			while (reader.hasNextLine()) {
				lines.add(reader.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return lines;
	}

	private void editFile(String route, int difficulty, List<String> lines) throws IOException {
		boolean shouldUpdate = readScore(difficulty) > score;

		if (shouldUpdate) {
			String lineToReplace = lines.get(difficulty);
			String prefix = lineToReplace.substring(0, lineToReplace.indexOf('=') + 1);
			lines.set(difficulty, prefix + " " + score);
		}

		try (FileOutputStream fos = new FileOutputStream(route);
		     OutputStreamWriter writer = new OutputStreamWriter(fos)) {

			for (String line : lines) {
				writer.write(line + "\n");
			}
		}
	}
}