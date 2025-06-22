package org.timinggame.api.room.exception;

import org.springframework.http.HttpStatus;

public class RoomExceededException extends RoomException {
	public RoomExceededException(String message) {
		super(message);
	}

	public RoomExceededException() {}

	public RoomExceededException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomExceededException(Throwable cause) {
		super(cause);
	}

	public RoomExceededException(
		String message,
		Throwable cause,
		boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
