package paint.data.util;

import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.scene.canvas.Canvas;
import paint.shapes.util.ShapeProperties;

public class XmlDataHandler {
	String xml;

	public XmlDataHandler() {
		xml = new String();

	}

	public void saveJson(HistoryEvent head) {
		XStream xstream = new XStream(new DomDriver());
		Collection<ShapeProperties> props = head.getShapesProperties();
		//xstream.alias("head", props.getClass());
		xstream.setMode(XStream.NO_REFERENCES);
		xml = xstream.toXML(props);
		System.out.println(xml);
	}

	@SuppressWarnings("unchecked")
	public HistoryEvent loadJson(Canvas canvas) {
		XStream xstream = new XStream(new DomDriver());
		ArrayList<ShapeProperties> props
		= (ArrayList<ShapeProperties>) xstream.fromXML(xml);
		System.out.println(props.getClass().getName());
		HistoryEvent head = new HistoryEvent(props);
		head.showEvent(canvas);
		return head;
	}
}
