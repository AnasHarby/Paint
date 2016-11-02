package paint.plugins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import paint.geom.ShapePaint;

public class PluginLoader {
	private static final String PATH
	= "bin/";
	private static final String SHAPE_PATH
	= "bin/paint/geom/";
	private static final String PACKAGE_NAME
	= "paint.geom.";
	private static final String KEY_FIELD
	= "KEY";
	private static final String CLASS_EXTENSION
	= ".class";

	public static String loadClass(File file) {
		try {
			System.out.println(file.getAbsolutePath());
			String classFile = file.getName();
			Path originalClassPath = Paths.get(file.toURI());
			Path newPath = Paths.get(SHAPE_PATH + classFile);
			Files.copy(originalClassPath, newPath);
			String className
			= classFile.replaceAll(CLASS_EXTENSION, "");
			URLClassLoader loader = URLClassLoader.
			newInstance(new
			URL[] { new
			File(PATH).toURI().toURL() });
			@SuppressWarnings("unchecked")
			Class<? extends ShapePaint> shapeClass
			= (Class<? extends ShapePaint>)
			loader.loadClass(PACKAGE_NAME + className);
			Class.forName(shapeClass.getName());
			Field keyField
			= shapeClass.getField(KEY_FIELD);
			String key = (String) keyField.get(null);
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
