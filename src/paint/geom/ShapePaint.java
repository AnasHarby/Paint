package paint.geom;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface ShapePaint {
	public void draw(Pane contentPane);

	public void remove(Pane contentPane);

	public void setBorderColor(Color col);

	public void fill(Color col);

	public String getIconUrl();

	public void setBorderWidth(double width);

	public void move(double x, double y);

	public void toBack();

	public void toFront();

	public void resize(double x1, double y1, double x2, double y2);
}
