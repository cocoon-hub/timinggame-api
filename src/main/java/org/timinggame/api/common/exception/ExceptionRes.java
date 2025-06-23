package org.timinggame.api.common.exception;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionRes {

	private LocalDateTime timeStamp;
	private String path;
	private String description;
	private String detail;

	public ExceptionRes(String path, String description, String detail) {
		this.timeStamp = LocalDateTime.now();
		this.path = path;
		this.description = description;
		this.detail = detail;
	}
}
