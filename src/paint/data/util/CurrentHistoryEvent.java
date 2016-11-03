package paint.data.util;

/**
 * The latest event done by
 * the user.
 */
public class CurrentHistoryEvent {

	/** The singleton instance. */
	private static CurrentHistoryEvent instance
	= new CurrentHistoryEvent();

	/** The actual history event. */
	private HistoryEvent head;

	/**
	 * Instantiates the singleton object.
	 */
	private CurrentHistoryEvent() {
		head = new HistoryEvent();
	}

	/**
	 * Gets the latest event.
	 * @return the latest event
	 */
	public HistoryEvent getHead() {
		return head;
	}

	/**
	 * Sets the latest event.
	 * @param modifiedHead the action done by the user
	 */
	public void setHead(HistoryEvent modifiedHead) {
		try {
			head = modifiedHead.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the singleton instance of CurrentHistoryEvent.
	 * @return singleton instance of CurrentHistoryEvent
	 */
	public static CurrentHistoryEvent getInstance() {
		return instance;
	}
}
