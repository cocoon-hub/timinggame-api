package org.timinggame.api.room.exception;

public class AlreadyGameFinishedException extends RuntimeException {
	public AlreadyGameFinishedException(String message) {
		super(message);
	}

	public AlreadyGameFinishedException() {}

	public AlreadyGameFinishedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyGameFinishedException(Throwable cause) {
		super(cause);
	}

	public AlreadyGameFinishedException(
		String message,
		Throwable cause,
		boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
