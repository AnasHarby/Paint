package paint.geom;


public class CirclePaint extends EllipsePaint {
	public CirclePaint(Point center, double radius) {
		super(center, radius, radius);
	}
	@Override
	public String getIconUrl() {
		return null;
	}
}
