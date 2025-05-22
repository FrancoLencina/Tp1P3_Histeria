package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

import Scores.ScoreFileEditor;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class EndScreen {

	JFrame frame;
	private int score;
	private int difficulty;
	private JLabel label;

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
		//frame.getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(50, 50, 50));
        label = new JLabel();
        label.setForeground(Color.WHITE);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);

		ScoreFileEditor sfe = new ScoreFileEditor("src/Scores/scores.txt", score);

		if (sfe.readScore(difficulty) < score) {
			label.setText("      ¡Felicitaciones!  " + "  \nGanaste en " + score + " turnos!"
					+ "  \nLa mejor puntuación fue de: " + sfe.readScore(difficulty));
		} else {
			sfe.replaceScore(difficulty);
			label.setText("    ¡Felicitaciones!  " + "  \nGanaste en " + score + " turnos!"
					+ "  \n¡Nueva puntuación récord!");
		}
		
		label.setFont(new Font("Arial Black", Font.PLAIN, 24));

		panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

	
	
	  frame.addComponentListener(new ComponentAdapter() {
          public void componentResized(ComponentEvent e) {
              changeFontSize();
          }
      });

      frame.setVisible(true);
      changeFontSize(); 
  }

	 private void changeFontSize() {
	        int width = frame.getWidth();
	        int newFontSize = Math.max(18, width / 40); 

	        label.setFont(new Font("Arial Black", Font.PLAIN, newFontSize));
	    }

}