package paint.data.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * The Class DataHandler.
 */
public class DataHandler {

	/** The extension of xml files. */
	private static final String XML_EXTENSION = ".xml";

	/** The extension of json files. */
	private static final String JSON_EXTENSION = ".json";

	/** The object that saves
	 * and loads the json data. */
	private JsonDataHandler jsonHandler;

	/** The object that saves
	 * and loads the json data. */
	private XmlDataHandler xmlHandler;

	/** The singleton instance. */
	private static DataHandler instance = new DataHandler();

	/**
	 * Instantiates the data handler singleton.
	 */
	private DataHandler() {
		jsonHandler = new JsonDataHandler();
		xmlHandler = new XmlDataHandler();
	}

	/**
	 * Gets the singleton instance of DataHandler.
	 * @return singleton instance of DataHandler
	 */
	public static DataHandler getInstance() {
		return instance;
	}

	/**
	 * Save data to a json file or
	 * an xml file depending on the
	 * file extension in the path.
	 * @param path the file path
	 * @param head the current history
	 * event to be saved
	 */
	public void saveData(String path, HistoryEvent head) {
		String data = "";
		if (path.endsWith(XML_EXTENSION)) {
			data = xmlHandler.saveXml(head);
		} else if (path.endsWith(JSON_EXTENSION)) {
			data = jsonHandler.getJson(head);
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

	/**
	 * Load the data from either JSON
	 * or XML files.
	 * @param path the file path
	 * @return the loaded history event
	 */
	public HistoryEvent loadData(String path) {
		HistoryEvent head = null;
		File loadFile = new File(path);
		loadFile = new File(path);
		if (path.endsWith(XML_EXTENSION)) {
			head = xmlHandler.loadXml(loadFile);
		} else if (path.endsWith(JSON_EXTENSION)) {
			head = jsonHandler.fromJson(loadFile);
		}
		return head;
	}
}
