package org.timinggame.api.room.exception;

import org.springframework.http.HttpStatus;

public class AlreadyGameStartedException extends RoomException {
	public AlreadyGameStartedException(String message) {
		super(message);
	}

	public AlreadyGameStartedException() {}

	public AlreadyGameStartedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyGameStartedException(Throwable cause) {
		super(cause);
	}

	public AlreadyGameStartedException(
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
