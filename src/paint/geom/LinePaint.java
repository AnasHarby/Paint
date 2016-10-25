package paint.geom;

public class LinePaint extends PolygonPaint {
	public LinePaint(Point point1, Point point2) {
		super(point1, point2);
	}
	@Override
	public String getIconUrl() {
		return null;
	}
}
