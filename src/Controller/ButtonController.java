package Controller;

import java.util.ArrayList;
import model.Button;
import model.COLOR;

public class ButtonController {

	private Button [][] ButtonMatrix;
	private int gridSize;
	
	public ButtonController(int gridSize) {
		ButtonMatrix = new Button[gridSize][gridSize];  
		this.gridSize = gridSize;
	}
	
	public void changeButtonColor(int row, int column){
		Button button = ButtonMatrix[row][column];
		button.changeColor();
	}

	public boolean checkMatch(int row, int column) {
		
		Button button = ButtonMatrix[row][column];
		
		for (Button neighbor : neighborhood(row,column)) {
			if (neighborON(neighbor) && neighbor.getColor() == button.getColor()) {
				return true;
			}
		}
		return false;
	}
	
	public void neighborsOFF(int row, int column) {
		Button button = ButtonMatrix[row][column];
		button.turnOff();
		for (Button neighbor : neighborhood(row, column)) {
			neighbor.turnOff();
		}
	}
	
	public void addNewButton(int row, int column) {
		Button newButton = new Button();
		ButtonMatrix [row][column] = newButton;
	}
	
	public boolean GameOver() {
		if(fullGrid())
			return true;
		return false;
	}
	
	public COLOR getButtonColor(int r, int c) {
		return ButtonMatrix[r][c].getColor();
	}

	private ArrayList<Button> neighborhood(int row, int column){
		
		ArrayList<Button> list = new ArrayList<Button>();
		
		if (column > 0) { 
			list.add(ButtonMatrix[row][column-1]);
		}
		
		if (column < gridSize-1) {
			list.add(ButtonMatrix[row][column+1]);
		}
		
		if (row > 0) {
			list.add(ButtonMatrix[row-1][column]);
		}
		
		if (row < gridSize-1) {
			list.add(ButtonMatrix[row+1][column]);
		}
		
		return list; 
	}

	private boolean neighborON(Button neighbor){
	
			if(neighbor.getColor() != COLOR.gray) {
				return true;
			}
		return false;
	}
	
	private boolean fullGrid() {
		for(int row = 0; row < ButtonMatrix.length; row++) {
			for(int column = 0; column < ButtonMatrix[0].length; column++) {
			if (ButtonMatrix[row][column].getColor() == COLOR.gray)
				return false;
			}
		}
		return true;
	}
}