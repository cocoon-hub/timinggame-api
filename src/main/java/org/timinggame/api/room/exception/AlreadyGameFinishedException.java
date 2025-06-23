package org.timinggame.api.room.exception;

import org.springframework.http.HttpStatus;

public class AlreadyGameFinishedException extends RoomException {
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

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
