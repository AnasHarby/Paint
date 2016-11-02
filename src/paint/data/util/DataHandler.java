package paint.data.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.scene.canvas.Canvas;

public class DataHandler {
	private static final String XML_EXTENSION = ".xml";
	private static final String JSON_EXTENSION = ".json";
	private JsonDataHandler jsonHandler;
	private XmlDataHandler xmlHandler;

	public DataHandler() {
		jsonHandler = new JsonDataHandler();
		xmlHandler = new XmlDataHandler();
	}

	public void saveData(String path, HistoryEvent head) {
		String data = "";
		if (path.endsWith(XML_EXTENSION)) {
			data = xmlHandler.saveXml(head);
		} else if (path.endsWith(JSON_EXTENSION)) {
			data = jsonHandler.saveJson(head);
		}
		File saveFile = new File(path);
		try {
			PrintWriter writer = new PrintWriter(saveFile);
			writer.print(data);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public HistoryEvent loadData(String path, Canvas canvas) {
		HistoryEvent head = null;
		File loadFile = new File(path);
		loadFile = new File(path);
		if (path.endsWith(XML_EXTENSION)) {
			head = xmlHandler.loadXml(loadFile, canvas);
		} else if (path.endsWith(JSON_EXTENSION)) {
			head = jsonHandler.loadJson(loadFile, canvas);
		}
		return head;
	}
}
