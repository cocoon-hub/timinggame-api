package org.timinggame.api.room.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomStatus {

	WAITING("WAITING"),
	IN_PROGRESS("IN_PROGRESS"),
	FINISHED("FINISHED"),
	;

	private String value;
}
