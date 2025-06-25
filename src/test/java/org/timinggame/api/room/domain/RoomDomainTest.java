package org.timinggame.api.room.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.timinggame.api.player.domain.PlayerDomain;

class RoomDomainTest {

	@Test
	void 방을_만들_때_방장과_입장코드가_필요하다() {
		// GIVEN
		final String pinCode = "123456";
		final String nickname = "닉네임";
		final PlayerDomain host = PlayerDomain.ofNew(nickname);

		// WHEN
		RoomDomain room = RoomDomain.ofNew(pinCode, host);

		// THEN
		assertThat(room).isNotNull();
		assertThat(room.getId()).isNotNull();
		assertThat(room.getCurrentRound()).isZero();
		assertThat(room.getHost()).isEqualTo(host);
		assertThat(room.getPlayers()).isEmpty();
		assertThat(room.getStartedAt()).isNull();
		assertThat(room.getFinishedAt()).isNull();
	}
}