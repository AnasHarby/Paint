package paint.data.util;

import java.util.Stack;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import paint.geom.ShapePaint;

public class History {
	private Stack<HistoryEvent> undoStack;
	private Stack<HistoryEvent> redoStack;
	private HistoryEvent currentEvent;
	
	public History() {
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		currentEvent = new HistoryEvent();
	}
	
	public HistoryEvent undo(Canvas canvas) {
		System.out.println("UNDO: " + undoStack.size());
		
		if (undoStack.isEmpty()) {
			System.out.println("" + undoStack.size());
			return null;
		} else {
			System.out.println("VALID UNDO");
			redoStack.push(currentEvent);
			undoStack.peek().showEvent(canvas);
			return currentEvent = undoStack.pop();
		}
	}

	public HistoryEvent redo(Canvas canvas) {
		System.out.println("REDO: " + redoStack.size());
		if (redoStack.isEmpty()) {
			return null;
		} else {
			undoStack.push(currentEvent);
			redoStack.peek().showEvent(canvas);
			return currentEvent = redoStack.pop();
		}
	}
	
	public void storeShapeChanges(HistoryEvent current) {
		HistoryEvent head = new HistoryEvent();
		for (ShapePaint shape : current.getShapes()) {
			try {
				head.getShapes().add(shape.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		undoStack.push(currentEvent);
		currentEvent = head;
		redoStack.clear();
		System.out.println("STORING SHAPE: " + undoStack.size());
	}
	
}
