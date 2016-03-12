package br.unifieo.sd.serverchat.data;

public class UserEvent {
	private int event;
	private UserChatListenerProcess userEvent;
	private String usernamePvtDest;
	private String message;

	public final static int EVENT_CONNECTION = 1;
	public final static int EVENT_DISCONNECTION = 2;
	public final static int EVENT_BROADCAST = 3;
	public final static int EVENT_PRIVATE = 4;

	public UserEvent(int event, UserChatListenerProcess userEvent, String message) {
		this.event = event;
		this.userEvent = userEvent;
		this.message = message;
	}

	public UserEvent(int event, UserChatListenerProcess userEvent, String usernamePvtDest, String message) {
		this(event, userEvent, message);
		this.usernamePvtDest = usernamePvtDest;
	}

	public int getEvent() {
		return event;
	}

	public UserChatListenerProcess getUserEvent() {
		return userEvent;
	}

	public String getUsernamePvtDest() {
		return usernamePvtDest;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "UserEvent [event=" + event + ", userEvent=" + userEvent.getUsername() + ", message=" + message + "]";
	}

}
