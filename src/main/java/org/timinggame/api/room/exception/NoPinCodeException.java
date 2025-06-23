package org.timinggame.api.room.exception;

import org.springframework.http.HttpStatus;

public class NoPinCodeException extends RoomException {
	public NoPinCodeException(String message) {
		super(message);
	}

	public NoPinCodeException() {}

	public NoPinCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoPinCodeException(Throwable cause) {
		super(cause);
	}

	public NoPinCodeException(
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
