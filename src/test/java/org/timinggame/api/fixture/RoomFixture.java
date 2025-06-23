package org.timinggame.api.fixture;

import java.time.LocalDateTime;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.domain.RoomStatus;

public final class RoomFixture {

	public static Room waitingRoom(final Long roomId) {
		return Room.builder()
			.roomId(roomId)
			.pinCode("123456789")
			.status(RoomStatus.WAITING)
			.startedAt(null)
			.finishedAt(null)
			.loserId(null)
			.createdAt(LocalDateTime.now())
			.build();
	}

	public static Room inProgressRoom(
		final Long roomId, final LocalDateTime startedAt, final LocalDateTime finishedAt) {
		return Room.builder()
			.roomId(roomId)
			.pinCode("123456789")
			.status(RoomStatus.IN_PROGRESS)
			.startedAt(startedAt)
			.finishedAt(finishedAt)
			.loserId(null)
			.createdAt(LocalDateTime.now())
			.build();
	}

	public static Room finishedRoom(final Long roomId, final LocalDateTime startedAt, final LocalDateTime finishedAt,
		final Long loserId) {
		return Room.builder()
			.roomId(roomId)
			.pinCode("123456789")
			.status(RoomStatus.FINISHED)
			.startedAt(startedAt)
			.finishedAt(finishedAt)
			.loserId(loserId)
			.createdAt(LocalDateTime.now())
			.build();
	}
}
