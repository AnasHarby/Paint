package paint.geom;


public class SquarePaint extends RectanglePaint {
	public SquarePaint(Point upperLeft, double length) {
		super(upperLeft, length, length);
	}
	@Override
	public String getIconUrl() {
		return null;
	}
}
