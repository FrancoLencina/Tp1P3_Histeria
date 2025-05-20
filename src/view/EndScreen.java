package view;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import Scores.ScoreFileEditor;
import java.awt.Font;

public class EndScreen {

	JFrame frame;
	private int score;
	private int difficulty;

	/**
	 * Create the application.
	 */
	public EndScreen(int score, int difficulty) {
		this.score = score;
		this.difficulty = difficulty;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("static-access")
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Hysteria");
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
	    frame.setBounds(100, 100, 720, 480);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.getContentPane().setLayout(new CardLayout(350, 300));

		JTextArea txt = new JTextArea();
		txt.setFont(new Font("Arial Black", Font.PLAIN, 12));
		txt.setForeground(new Color(255, 255, 255));
		txt.setBackground(new Color(50, 50, 50));

		ScoreFileEditor sfe = new ScoreFileEditor("src/Scores/scores.txt", score);

		if (sfe.readScore(difficulty) < score) {
			txt.setText("      ¡Felicitaciones!" + "\nGanaste en " + score + " turnos!"
					+ "\nLa mejor puntuación fue de: " + sfe.readScore(difficulty));
		} else {
			sfe.replaceScore(difficulty);
			txt.setText("    ¡Felicitaciones!" + "\nGanaste en " + score + " turnos!"
					+ "\n¡Nueva puntuación récord!");
		}

		frame.getContentPane().add(txt);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}