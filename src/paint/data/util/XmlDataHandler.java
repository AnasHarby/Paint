package paint.data.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.scene.canvas.Canvas;
import paint.shapes.util.ShapeProperties;

public class XmlDataHandler {


	public String saveXml(HistoryEvent head) {
		String xml = new String();
		XStream xstream = new XStream(new DomDriver());
		Collection<ShapeProperties> props = head.getShapesProperties();
		//xstream.alias("head", props.getClass());
		xstream.setMode(XStream.NO_REFERENCES);
		xml = xstream.toXML(props);
		System.out.println(xml);
		return xml;
	}

	@SuppressWarnings("unchecked")
	public HistoryEvent loadXml(File xml, Canvas canvas) {
		XStream xstream = new XStream(new DomDriver());
		ArrayList<ShapeProperties> props
		= (ArrayList<ShapeProperties>) xstream.fromXML(xml);
		HistoryEvent head = new HistoryEvent(props);
		head.showEvent(canvas);
		return head;
	}
}
