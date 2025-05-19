package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;

import Scores.Score;

import java.awt.CardLayout;
import java.awt.Font;

public class EndScreen {

	Dimension screenSize;
	JFrame frame;
	int score;
	private int clicks;
	private int difficulty;

	/**
	 * Create the application.
	 * @param gridSize 
	 */
	public EndScreen(int clicks, int gridSize) {
		this.clicks = clicks;
		this.difficulty = gridSize-4;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Hysteria");
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.getContentPane().setLayout(new CardLayout(350, 300));
		
		JTextArea txt = new JTextArea();
		txt.setFont(new Font("Arial Black", Font.PLAIN, 12));
		txt.setForeground(new Color(255, 255, 255));
		txt.setBackground(new Color(50, 50, 50));
		if (Score.highScore(difficulty) < clicks)
		txt.setText("      Felicitaciones!" + "\nGanaste en "+ clicks +" turnos!"
				+ "\n La mejor puntuacion fue de:" + Score.highScore(difficulty));
		else {
			Score.scoreReplace(difficulty);
			txt.setText("    Felicitaciones!" + "\nGanaste en "+ clicks +" turnos!"
					+ "\nNueva puntuacion record");
		}
		frame.getContentPane().add(txt);
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
