package org.timinggame.api.room.exception;

import org.springframework.http.HttpStatus;

public abstract class RoomException extends RuntimeException {

	public abstract HttpStatus getStatus();

	protected RoomException() {}

	protected RoomException(String message) {
		super(message);
	}

	protected RoomException(String message, Throwable cause) {
		super(message, cause);
	}

	protected RoomException(Throwable cause) {
		super(cause);
	}

	protected RoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
