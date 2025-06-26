package org.timinggame.api.room.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.timinggame.api.player.domain.PlayerDomain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomDomain {

	private String id;
	private String pinCode;
	private RoomStatus status;
	private int currentRound;
	private PlayerDomain host;
	private Set<PlayerDomain> players;

	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;

	public static RoomDomain ofNew(final String pinCode, final PlayerDomain host) {
		final String id = UUID.randomUUID().toString();
		return RoomDomain.builder()
			.id(id)
			.pinCode(pinCode)
			.status(RoomStatus.WAITING)
			.host(host)
			.startedAt(null)
			.finishedAt(null)
			.players(new HashSet<>())
			.currentRound(0)
			.build();
	}

	public boolean start() {
		if (players.isEmpty()) {
			return false;
		}

		final long unreadyPlayers = players.stream()
			.filter(it -> !it.isReady())
			.count();

		if (unreadyPlayers == 0L) {
			this.status = RoomStatus.IN_PROGRESS;
			this.currentRound = 1;
			this.startedAt = LocalDateTime.now();
			return true;
		}
		return false;
	}

	public void finish() {
		this.finishedAt = LocalDateTime.now();
	}

	public void addPlayer(PlayerDomain player) {
		players.add(player);
	}

	public void removePlayer(PlayerDomain player) {
		players.remove(player);
	}
}
