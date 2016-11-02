package paint.data.util;


import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import javafx.scene.canvas.Canvas;
import paint.shapes.util.ShapeProperties;

public class JsonDataHandler {

	public String saveJson(HistoryEvent head) {
		String json = new String();
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		Collection<ShapeProperties> props = head.getShapesProperties();
		//xstream.alias("head", props.getClass());
		xstream.setMode(XStream.NO_REFERENCES);
		json = xstream.toXML(props);
		System.out.println(json);
		return json;
	}

	@SuppressWarnings("unchecked")
	public HistoryEvent loadJson(String json, Canvas canvas) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		ArrayList<ShapeProperties> props
		= (ArrayList<ShapeProperties>) xstream.fromXML(json);
		System.out.println(props.getClass().getName());
		HistoryEvent head = new HistoryEvent(props);
		head.showEvent(canvas);
		return head;
	}
}
