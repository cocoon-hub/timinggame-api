package org.timinggame.api.room.exception;

import org.springframework.http.HttpStatus;

public class NoRoomException extends RoomException {
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

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
