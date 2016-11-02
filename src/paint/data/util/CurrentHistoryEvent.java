package paint.data.util;

public class CurrentHistoryEvent {
	private static CurrentHistoryEvent instance
	= new CurrentHistoryEvent();
	private HistoryEvent head;

	private CurrentHistoryEvent() {
		head = new HistoryEvent();
	}

	public HistoryEvent getHead() {
		return head;
	}

	public void setHead(HistoryEvent modifiedHead) {
		try {
			head = modifiedHead.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public static CurrentHistoryEvent getInstance() {
		return instance;
	}
}
