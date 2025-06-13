package org.timinggame.api.room.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
