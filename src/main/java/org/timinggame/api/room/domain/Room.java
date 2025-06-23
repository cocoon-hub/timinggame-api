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

	public void finishGame() throws RuntimeException {
		this.status = RoomStatus.FINISHED;
		this.finishedAt = LocalDateTime.now();
		// TODO: loserId를 초기화한다
	}

	public boolean isStarted() {
		return RoomStatus.IN_PROGRESS.equals(this.status);
	}

	public boolean isFinished() {
		return RoomStatus.FINISHED.equals(this.status);
	}
}
