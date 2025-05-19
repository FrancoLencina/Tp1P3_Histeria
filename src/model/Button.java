package model;

import java.util.Random;

public class Button {
	
	COLOR color;

	public Button() {
		this.color = COLOR.gray;
	}
	
	public void changeColor() {
		Random random = new Random();
		int randomColor = random.nextInt(COLOR.values().length-1);
		switch(randomColor) {
		case 0:
			color = COLOR.red;
			break;
		case 1:
			color = COLOR.blue;
			break;
		case 2:
			color = COLOR.green;
			break;
		case 3:
			color = COLOR.yellow;
			break;
		case 4:
			color = COLOR.cyan;
			break;
		case 5:
			color = COLOR.magenta;
			break;
		}
	}

	public void turnOff() {
		this.color = COLOR.gray;
	}
}
