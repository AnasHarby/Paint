package paint.data.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import paint.shapes.util.ShapeProperties;

/**
 * The class responsible for serializing
 *  data to an xml string.
 */
public class XmlDataHandler {

	/**
	 * Gets the current state xml string.
	 * @param head the current state
	 * @return the xml string representing
	 * the current state
	 */
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
	/**
	 * Gets the current state from
	 * an xml file.
	 * @param xml the xml file;
	 * @return the history event
	 */
	@SuppressWarnings("unchecked")
	public HistoryEvent fromXml(File xml) {
		XStream xstream = new XStream(new DomDriver());
		ArrayList<ShapeProperties> props
		= (ArrayList<ShapeProperties>) xstream.fromXML(xml);
		HistoryEvent head = new HistoryEvent(props);
		return head;
	}
}
