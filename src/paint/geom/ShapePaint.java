package paint.geom;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface ShapePaint {
	
	public void draw(Pane contentPane);
	public void setBorderColor(Color col);
	public void fill(Color col);
	public String getIconUrl();
	public void setBorderWidth(double width);
	public void move(double x, double y);
	public void toBack();
	public void toFront();
	//public void resize(ResizeHelper helper);
}
