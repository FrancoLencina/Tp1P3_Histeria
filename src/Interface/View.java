package Interface;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class View {

	JFrame frame;
	int gridSize;
	JButton[][] JButtonMatrix;
	ButtonController buttonController;
	Dimension screenSize; 
	/**
	 * Create the application.
	 */
	public View(int size) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //para conocer el tamaño del monitor
		if (size > 7)
			throw new IllegalArgumentException("");
		initialize(size);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int i) { // i = número de filas y columnas
		//Creo una matriz con los JButtons
		gridSize = i;
		JButtonMatrix = new JButton[i][i];
		frame = new JFrame();
		frame.setTitle("Hysteria");
		frame.getContentPane().setBackground(new Color(60, 60, 60));
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(i, i, 1, 1));
		
	
		//Creamos un presentador
		buttonController = new ButtonController(i);
		
		//Creamos todos los botones y los agregamos a la matriz JButtonMatrix
		for(int row = 0; row < JButtonMatrix.length; row++) {
			for(int column = 0; column < JButtonMatrix[0].length; column++) {
			JButton button = new JButton("");
			button.setBackground(new Color(50,50,50));
			frame.getContentPane().add(button);
			JButtonMatrix[row][column] = button;
			
		//Creamos el objeto Button asociado al JButton
			buttonController.addNewButton(row,column);
		 }
		}

		//Recorremos los JButtons informando al buttonController cuando uno esté activo
		for(int row = 0; row < JButtonMatrix.length; row++) {
			for(int column = 0; column < JButtonMatrix[0].length; column++) {
				int r=row;
				int c=column;
				JButtonMatrix[row][column].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean match = buttonController.activeButton(r,c);
					if (match) {
						turnOffNeighbors(r, c);
						JButtonMatrix[r][c].setBackground(new Color(50, 50, 50));
					}else{
						updateViews(r, c);
					}
				}
				});
			}
		}
	}

	private void turnOffNeighbors(int row, int column) {
		
		if (row+1 < gridSize) {
			JButtonMatrix[row+1][column].setBackground(new Color(50, 50, 50));
		}
		if (row-1 >= 0) {
			JButtonMatrix[row-1][column].setBackground(new Color(50, 50, 50));
		}
		if (column+1 < gridSize) {
			JButtonMatrix[row][column+1].setBackground(new Color(50, 50, 50));
		}
		if (column-1 >= 0) {
			JButtonMatrix[row][column-1].setBackground(new Color(50, 50, 50));
		}
		
	}

	private void updateViews(int r, int c) {
		
		if (buttonController.win) {
			EndScreen end = new EndScreen();
			frame.dispose();
			end.frame.setVisible(true);
		}
		
		COLOR color = buttonController.ButtonMatrix[r][c].color; 
		switch(color) {
		case red:
			JButtonMatrix[r][c].setBackground(new Color(255,0,0));
			break;
		case blue:
			JButtonMatrix[r][c].setBackground(new Color(0,0,255));
			break;
		case green:
			JButtonMatrix[r][c].setBackground(new Color(0,255,0));
			break;
		case yellow:
			JButtonMatrix[r][c].setBackground(new Color(255,255,0));
			break;
		case cyan:
			JButtonMatrix[r][c].setBackground(new Color(0,255,255));
			break;
		case magenta:
			JButtonMatrix[r][c].setBackground(new Color(255,0,255));
			break;
		default:
			JButtonMatrix[r][c].setBackground(new Color(50,50,50));
			break;
		}
	}
}
