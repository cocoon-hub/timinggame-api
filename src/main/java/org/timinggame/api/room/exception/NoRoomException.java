package org.timinggame.api.room.exception;

public class NoRoomException extends RuntimeException {
	public NoRoomException(String message) {
		super(message);
	}

	public NoRoomException() {}

	public NoRoomException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRoomException(Throwable cause) {
		super(cause);
	}

	public NoRoomException(
		String message,
		Throwable cause,
		boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
