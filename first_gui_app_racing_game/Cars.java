

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cars extends Rectangle{
	private Color color;
	String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	Cars(int x,int y,int w,int h,String type, Color color){
		super(w,h,color);
		this.type = type;
		setTranslateX(x);
		setTranslateY(y);
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
