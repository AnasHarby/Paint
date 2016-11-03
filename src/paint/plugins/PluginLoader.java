package paint.plugins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

import paint.geom.ShapePaint;

public class PluginLoader {
	private static final String PACKAGE_NAME
	= "paint.geom.";
	private static final String KEY_FIELD
	= "KEY";
	private static final String CLASS_EXTENSION
	= ".class";
	private static final String JAR_EXTENSION
	= ".jar";
	private static final String PACKAGE_DIR = "paint\\geom\\";
	public static String loadClass(File file) {
		if (file == null) {
			return null;
		}
		try {
			String classFile = file.getName();
			String className = "";
			String filePath = file.getPath();
			if (classFile.endsWith(CLASS_EXTENSION)) {
				className = classFile.replaceAll(CLASS_EXTENSION, "");
				if (file.getPath().contains(PACKAGE_DIR)) {
					file = new File(filePath.substring(0, filePath.indexOf(PACKAGE_DIR)));
				}
				System.out.println(file.getPath());
			} else if (classFile.endsWith(JAR_EXTENSION)) {
				className = classFile.replaceAll(JAR_EXTENSION, "");
			}
			URLClassLoader loader = URLClassLoader.
			newInstance(new
			URL[] { file.toURI().toURL() });
			@SuppressWarnings("unchecked")
			Class<? extends ShapePaint> shapeClass
			= (Class<? extends ShapePaint>)
			loader.loadClass(PACKAGE_NAME + className);
			Field keyField
			= shapeClass.getField(KEY_FIELD);
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
