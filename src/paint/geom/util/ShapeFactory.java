package paint.geom.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import paint.geom.ShapePaint;
import paint.shapes.util.ShapeProperties;

public class ShapeFactory {
	private static ShapeFactory factory = new ShapeFactory();
	private HashMap<String, Class<? extends ShapePaint>> registeredShapes = null;

	private ShapeFactory() {
		registeredShapes = new HashMap<>();
	}

	public static ShapeFactory getInstance() {
		return factory;
	}

	public void registerShape(String shapeID,
			Class<? extends ShapePaint> shapeClass) {
		registeredShapes.put(shapeID, shapeClass);
		System.out.println("Shape: " + shapeID + " Registered");
	}

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
}
