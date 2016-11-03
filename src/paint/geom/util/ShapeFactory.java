package paint.geom.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import paint.geom.ShapePaint;
import paint.shapes.util.ShapeProperties;
/**
 * The factory class that creates the
 * shapes while hiding their creation
 * process from the user
 */
public class ShapeFactory {
	/**
	 * The registered shapes in the factory.
	 */
	private HashMap<String, Class<? extends ShapePaint>> registeredShapes = null;
	/**
	 * The factory singleton instance.
	 */
	private static ShapeFactory factory = new ShapeFactory();
	/**
	 * Creates the factory singleton instance.
	 */
	private ShapeFactory() {
		registeredShapes = new HashMap<>();
	}
	/**
	 * Gets the singleton factory instance.
	 * @return the singleton factory instance.
	 */
	public static ShapeFactory getInstance() {
		return factory;
	}
	/**
	 * Registers a shape to the factory.
	 * @param shapeID The id of the shape class
	 * to be registered.
	 * @param shapeClass The class to be registered.
	 */
	public void registerShape(String shapeID,
			Class<? extends ShapePaint> shapeClass) {
		registeredShapes.put(shapeID, shapeClass);
		System.out.println("Shape: " + shapeID + " Registered");
	}
	/**
	 * Creates a shape given its vertices' x, y
	 * coordinates.
	 * @param shapeID The id of the shape to be created
	 * @param properties The x, y coordinates of the
	 * vertices of the shape
	 * @return The created shape
	 */
	public ShapePaint createShape(String shapeID, double... properties) {
		Class<? extends ShapePaint> shapeClass =
				registeredShapes.get(shapeID);
		try {
			Constructor<? extends ShapePaint> shapeConstructor =
					shapeClass.getConstructor(double[].class);
			ShapePaint shape = shapeConstructor.newInstance(properties);
			return shape;
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException e) {
			return null;
		}
	}
	/**
	 * Creates a shape given its equivalent
	 * {@link ShapeProperties}
	 * object representing all of its properties.
	 * @param shapeID The id of the shape to be created
	 * @param properties The equivalent
	 * {@link ShapeProperties}
	 * object representing all of the shape's properties
	 * @return The created shape
	 */
	public ShapePaint createShape(String shapeID, ShapeProperties properties) {
		Class<? extends ShapePaint> shapeClass =
				registeredShapes.get(shapeID);
		try {
			Constructor<? extends ShapePaint> shapeConstructor =
					shapeClass.getConstructor(ShapeProperties.class);
			ShapePaint shape = shapeConstructor.newInstance(properties);
			return shape;
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException e) {
			return null;
		}
	}
	/**
	 * Gets a map of the registered shape in the factory
	 * @return The map representing
	 * the classes registered in the factory
	 * and their corresponding class key.
	 */
	public HashMap<String, Class<? extends ShapePaint>> getRegisteredShapes() {
		return registeredShapes;
	}
}
