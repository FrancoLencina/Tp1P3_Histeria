package Controller;

import java.awt.Color;
import javax.swing.JButton;
import model.COLOR;

public class GameScreenController {
	private JButton[][] JButtonMatrix;

	public GameScreenController(int size) {
		this.JButtonMatrix = new JButton[size][size];
	}
   
	public void turnOffNeighbors(int row, int column) {
	   
	    JButtonMatrix[row][column].setBackground(new Color(50, 50, 50));
		
		if (row+1 < getSize()) {
			JButtonMatrix[row+1][column].setBackground(new Color(50, 50, 50));
		}
		if (row-1 >= 0) {
			JButtonMatrix[row-1][column].setBackground(new Color(50, 50, 50));
		}
		if (column+1 < getSize()) {
			JButtonMatrix[row][column+1].setBackground(new Color(50, 50, 50));
		}
		if (column-1 >= 0) {
			JButtonMatrix[row][column-1].setBackground(new Color(50, 50, 50));
		}
		
	}

	public void changeColor(int row, int column, COLOR color) {
		
		switch(color) {
		case red:
			JButtonMatrix[row][column].setBackground(new Color(255,0,0));
			break;
		case blue:
			JButtonMatrix[row][column].setBackground(new Color(0,0,255));
			break;
		case green:
			JButtonMatrix[row][column].setBackground(new Color(0,255,0));
			break;
		case yellow:
			JButtonMatrix[row][column].setBackground(new Color(255,255,0));
			break;
		case cyan:
			JButtonMatrix[row][column].setBackground(new Color(0,255,255));
			break;
		case magenta:
			JButtonMatrix[row][column].setBackground(new Color(255,0,255));
			break;
		default:
			JButtonMatrix[row][column].setBackground(new Color(50,50,50));
			break;
		}
	}
	
	public JButton[][] getJButtonMatrix() {
		return JButtonMatrix;
	}
	
	public JButton getJButton(int row, int column) {
		return JButtonMatrix[row][column];
	}
	
	public void addToJButtonMatrix(int row, int column, JButton button) {
		JButtonMatrix[row][column] = button;
	}
	
	public int getSize() {
		return JButtonMatrix.length;
	}
}