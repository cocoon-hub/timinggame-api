package org.timinggame.api.room.repository.redis;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.timinggame.api.room.domain.RoomDomain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomDao implements Serializable {

	private String id;
	private String pinCode;
	private String status;
	private int currentRound;
	private String hostId;
	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;

	public static RoomDao from(RoomDomain room) {
		return RoomDao.builder()
			.id(room.getId())
			.pinCode(room.getPinCode())
			.status(room.getStatus().getValue())
			.currentRound(room.getCurrentRound())
			.hostId(room.getHost().getId())
			.startedAt(room.getStartedAt())
			.finishedAt(room.getFinishedAt())
			.build();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		RoomDao roomDao = (RoomDao)o;
		return Objects.equals(getId(), roomDao.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}
}
