package paint.data.util;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import paint.data.util.history.HistoryEvent;
import paint.geom.prop.ShapeProperties;

/**
 * The class responsible for serializing
 *  data to a json string.
 */
public class JsonDataParser {

	/**
	 * Gets the current state json string.
	 * @param head the current state
	 * @return the json string representing
	 * the current state
	 */
	public String getJson(HistoryEvent head) {
		String json = new String();
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		Collection<ShapeProperties> props = head.getShapesProperties();
		//xstream.alias("head", props.getClass());
		xstream.setMode(XStream.NO_REFERENCES);
		json = xstream.toXML(props);
		System.out.println(json);
		return json;
	}

	/**
	 * Gets the current state from
	 * a json file.
	 * @param json the json file;
	 * @return the history event
	 */
	@SuppressWarnings("unchecked")
	public HistoryEvent fromJson(File json) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		ArrayList<ShapeProperties> props
		= (ArrayList<ShapeProperties>) xstream.fromXML(json);
		HistoryEvent head = new HistoryEvent(props);
		return head;
	}
}
