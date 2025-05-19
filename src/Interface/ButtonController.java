package Interface;

import java.util.ArrayList;

public class ButtonController {

	Button [][] ButtonMatrix;
	int boardSize;
	boolean win;
	
	public ButtonController(int boardSize) {
		ButtonMatrix = new Button[boardSize][boardSize];  
		this.boardSize = boardSize;
		win = false;
	}
	
	
	public boolean activeButton(int row, int column){
		boolean result = false;
		General.buttonPressed();
		Button button = ButtonMatrix[row][column];
		button.changeColor();
		if (colorMatch(button,neighborhood(row, column))) {
			neighborsOFF(row,column);
			result = true;
			return result;
		}
		else
			if (fullGrid()) {
				win = true;
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
}


