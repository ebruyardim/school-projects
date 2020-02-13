

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle{

	boolean dead = false;
	final String type;
	
	Player(int x,int y,int w,int h,String type, Color color){
		super(w,h,color);
		this.type = type;
		setTranslateX(x);
		setTranslateY(y);
		
	}
	
	
	void moveLeft() {
		setTranslateX(getTranslateX() - 10);	
		if ((getTranslateX()==110)) {
			moveRight();
		}
	}



	void moveRight() {
		setTranslateX(getTranslateX() + 10);
		if ((getTranslateX()==440)) {
			moveLeft();
		}
	}
	
	










}
