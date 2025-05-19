package Controller;

import java.util.ArrayList;

import Scores.Score;
import model.Button;
import model.COLOR;

public class ButtonController {

	private Button [][] ButtonMatrix;
	private int boardSize;
	private boolean gameOver;
	
	public ButtonController(int boardSize) {
		ButtonMatrix = new Button[boardSize][boardSize];  
		this.boardSize = boardSize;
		gameOver = false;
	}
	
	
	public boolean activeButton(int row, int column){
		boolean result = false;
		Button button = ButtonMatrix[row][column];
		button.changeColor();
		if (colorMatch(button,neighborhood(row, column))) {
			neighborsOFF(row,column);
			result = true;
			return result;
		}
		else
			if (fullGrid()) {
				gameOver = true;
			}
		return result;
	}

	public void addNewButton(int row, int column) {
		Button newButton = new Button();
		ButtonMatrix [row][column] = newButton;
	}

	private ArrayList<Button> neighborhood(int row, int column){
		
		ArrayList<Button> list = new ArrayList<Button>();
		
		if (column > 0) { 
			list.add(ButtonMatrix[row][column-1]);
		}
		
		if (column < boardSize-1) {
			list.add(ButtonMatrix[row][column+1]);
		}
		
		if (row > 0) {
			list.add(ButtonMatrix[row-1][column]);
		}
		
		if (row < boardSize-1) {
			list.add(ButtonMatrix[row+1][column]);
		}
		
		return list; 
	}

	private boolean neighborON(Button neighbor){
	
			if(neighbor.color != COLOR.gray) {
				return true;
			}
		return false;
	}
	
	private void neighborsOFF(int row, int column) {
		Button button = ButtonMatrix[row][column];
		if (colorMatch(button, neighborhood(row, column))) {
			
			button.turnOff();
			for (Button neighbor : neighborhood(row, column)) {
				neighbor.turnOff();
			}
		}
	}
	
	private boolean colorMatch(Button button, ArrayList<Button> neighborhood) {
		
		for (Button neighbor : neighborhood) {
			if (neighborON(neighbor) && neighbor.color == button.color) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean fullGrid() {
		for(int row = 0; row < ButtonMatrix.length; row++) {
			for(int column = 0; column < ButtonMatrix[0].length; column++) {
			if (ButtonMatrix[row][column].color == COLOR.gray)
				return false;
			}
		}
		return true;
	}


	public boolean getGameOver() {
		return gameOver;
	}
}


