package view;

import javax.swing.JFrame;
import Controller.ButtonController;
import Controller.GameScreenController;
import model.COLOR;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameScreen {

	JFrame frame;
	int gridSize;
	ButtonController buttonController;
	GameScreenController GSController;
	int score;
	private int difficulty;
	
	
	/**
	 * Create the application.
	 * @param difficulty 
	 */
	public GameScreen(int size, int difficulty) {
		this.score = 0;
		this.difficulty = difficulty;
		if (size > 7)
			throw new IllegalArgumentException("");
		initialize(size);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("static-access")
	private void initialize(int size) { 
		gridSize = size;
		frame = new JFrame();
		frame.setTitle("Hysteria");
		frame.getContentPane().setBackground(new Color(60, 60, 60));
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(size, size, 1, 1));
		
		buttonController = new ButtonController(size);
		GSController = new GameScreenController(size);
		
		for(int row = 0; row < GSController.getSize(); row++) {
			for(int column = 0; column < GSController.getSize(); column++) {
			JButton button = new JButton("");
			button.setBackground(new Color(50,50,50));
			frame.getContentPane().add(button);
			GSController.addToJButtonMatrix(row, column, button);
			buttonController.addNewButton(row,column);
		 }
		}

		for(int row = 0; row < GSController.getSize(); row++) {
			for(int column = 0; column < GSController.getSize(); column++) {
				int r=row;
				int c=column;
				GSController.getJButton(r,c).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						score++;
						boolean match = buttonController.activeButton(r,c);
						if (match) {
				            GSController.turnOffNeighbors(r, c);
				        } else {
				            updateViews(r, c);
				        }
					}
				});
			}
		}
	}
	
	private void updateViews(int r, int c) {
        if (buttonController.GameOver()) {
            EndScreen end = new EndScreen(score, difficulty);
            frame.dispose();
            end.frame.setVisible(true);
        } else {
            COLOR color = buttonController.getButtonColor(r, c); 
            GSController.changeColor(r, c, color);
        }
	}
}