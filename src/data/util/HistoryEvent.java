package data.util;

import java.util.ArrayList;
import java.util.Collection;

import paint.geom.ShapePaint;

public class HistoryEvent {
	private Collection<ShapePaint> shapes;
	
	public HistoryEvent() {
		shapes = new ArrayList<>();
	}
	
	public Collection<ShapePaint> getShapes() {
		return shapes;
	}
}
