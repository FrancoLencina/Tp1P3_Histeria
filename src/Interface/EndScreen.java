package Interface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import java.awt.Font;

public class EndScreen {

	Dimension screenSize;
	JFrame frame;
	int score;

	/**
	 * Create the application.
	 */
	public EndScreen() {
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
		if (General.highScore(General.difficulty) < General.clicks)
		txt.setText("      Felicitaciones!" + "\nGanaste en "+ General.clicks +" turnos!"
				+ "\n La mejor puntuacion fue de:" + General.highScore(General.difficulty));
		else {
			General.scoreReplace(General.difficulty);
			txt.setText("    Felicitaciones!" + "\nGanaste en "+ General.clicks +" turnos!"
					+ "\nNueva puntuacion record");
		}
		frame.getContentPane().add(txt);
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
