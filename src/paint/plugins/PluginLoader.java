package paint.plugins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

import paint.geom.ShapePaint;

/**
 * The class responsible for loading
 * plugins from .class files or
 * .jar files.
 */
public class PluginLoader {

	/** The name of the geometry package. */
	private static final String PACKAGE_NAME
	= "paint.geom.";

	/** The name of the key field. */
	private static final String KEY_FIELD
	= "KEY";

	/** The suffix of class files. */
	private static final String CLASS_EXTENSION
	= ".class";

	/** The suffix of jar files. */
	private static final String JAR_EXTENSION
	= ".jar";

	/** The dir of the package. */
	private static final String PACKAGE_DIR = "paint\\geom\\";

	/**
	 * The method responsible for
	 * loading the class files.
	 * @param file a jar file or a
	 * .class file with the package
	 * directory in its path
	 * @return the key of the loaded
	 * class
	 */
	public static String loadClass(File file) {
		if (file == null) {
			return null;
		}
		try {
			String classFile = file.getName();
			String className = "";
			String filePath = file.getPath();
			if (classFile.endsWith(CLASS_EXTENSION)) {
				// Get the class name from file name.
				className = classFile.replaceAll(CLASS_EXTENSION, "");
				/*
				 * The path of the class file must
				 * contain the package directory.
				 */
				if (file.getPath().contains(PACKAGE_DIR)) {
					file = new File(filePath.substring(0,
							filePath.indexOf(PACKAGE_DIR)));
				}
				System.out.println(file.getPath());
			} else if (classFile.endsWith(JAR_EXTENSION)) {
				// Get the class name from file name.
				className = classFile.replaceAll(JAR_EXTENSION, "");
			}
			URLClassLoader loader = URLClassLoader.
			newInstance(new
			URL[] { file.toURI().toURL() });
			@SuppressWarnings("unchecked")
			// Load the class.
			Class<? extends ShapePaint> shapeClass
			= (Class<? extends ShapePaint>)
			loader.loadClass(PACKAGE_NAME + className);
			// Get the key field of the loaded class.
			Field keyField
			= shapeClass.getField(KEY_FIELD);
			// Get the key value of the loaded class.
			String key = (String) keyField.get(null);
			loader.close();
			return key;
		} catch (ClassNotFoundException
				| IllegalArgumentException
				| IllegalAccessException
				| NoSuchFieldException
				| SecurityException
				| IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
