package paint.data.util;

import java.util.Stack;

import javafx.scene.canvas.Canvas;

// TODO: Auto-generated Javadoc
/**
 * The class responsible for
 * undoing and redoing operations.
 */
public class History {

	/** The undo stack. */
	private Stack<HistoryEvent> undoStack;

	/** The redo stack. */
	private Stack<HistoryEvent> redoStack;

	/** The current event. */
	private HistoryEvent currentEvent;

	/** The singleton history object. */
	private static History history = new History();

	/**
	 * Instantiates a new singleton
	 * object.
	 */
	private History() {
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		currentEvent = null;
	}

	/**
	 * Gets the history object.
	 * @return the history singleton
	 */
	public static History getHistory() {
		return history;
	}

	/**
	 * Clears the history.
	 */
	public static void clearHistory() {
		history = new History();
	}

	/**
	 * Undoes the last user operation.
	 * @param canvas the canvas on which
	 * the operations are drawn
	 * @return the previous history event
	 */
	public HistoryEvent undo(Canvas canvas) {
		System.out.println("UNDO: " + undoStack.size());

		if (undoStack.isEmpty()) {
			return null;
		} else {
			System.out.println("VALID UNDO");
			redoStack.push(currentEvent);
			currentEvent = undoStack.pop();
			return currentEvent;
		}
	}

	/**
	 * Redoes the next user operation.
	 * @param canvas the canvas on which
	 * the operations are drawn
	 * @return the previous history event
	 */
	public HistoryEvent redo(Canvas canvas) {
		System.out.println("REDO: " + redoStack.size());
		if (redoStack.isEmpty()) {
			return null;
		} else {
			undoStack.push(currentEvent);
			currentEvent = redoStack.pop();
			return currentEvent;
		}
	}


	/**
	 * Store shape changes to the history.
	 * @param the current history event
	 */
	public void storeShapeChanges(HistoryEvent current) {
		HistoryEvent head = new HistoryEvent();
		try {
			head = current.clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}

		if (currentEvent != null) {
			undoStack.push(currentEvent);
		}
		currentEvent = head;
		redoStack.clear();
		System.out.println("STORING SHAPE: " + undoStack.size());
	}
}
