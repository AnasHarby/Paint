package paint.geom.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import paint.geom.ShapePaint;

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
			System.out.println(shape.getId());
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
