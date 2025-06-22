package org.timinggame.api.room.exception;

import org.springframework.http.HttpStatus;

public abstract class RoomException extends RuntimeException {

	public abstract HttpStatus getStatus();

	public RoomException() {}

	public RoomException(String message) {
		super(message);
	}

	public RoomException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomException(Throwable cause) {
		super(cause);
	}

	public RoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
