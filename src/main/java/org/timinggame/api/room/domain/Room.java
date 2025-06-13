package org.timinggame.api.room.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {

	private Long roomId;
	private String pinCode;
	private RoomStatus status;
	private Long loserId;
	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;
	private LocalDateTime createdAt;

	public void startGame() {
		this.status = RoomStatus.IN_PROGRESS;
	}
}
