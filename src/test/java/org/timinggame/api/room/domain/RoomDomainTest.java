package org.timinggame.api.room.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.timinggame.api.player.domain.PlayerDomain;

class RoomDomainTest {

	@Test
	void 방을_만들_때_방장과_입장코드가_필요하다() {
		// GIVEN
		final String pinCode = "123456";
		final PlayerDomain host = PlayerDomain.ofNew("호스트");

		// WHEN
		final RoomDomain room = RoomDomain.ofNew(pinCode, host);

		// THEN
		assertThat(room).isNotNull();
		assertThat(room.getId()).isNotNull();
		assertThat(room.getPinCode()).isEqualTo(pinCode);
		assertThat(room.getStatus()).isEqualTo(RoomStatus.WAITING);
		assertThat(room.getCurrentRound()).isZero();
		assertThat(room.getHost()).isEqualTo(host);
		assertThat(room.getPlayers()).isEmpty();
		assertThat(room.getStartedAt()).isNull();
		assertThat(room.getFinishedAt()).isNull();
	}

	@Nested
	class 게임을_시작할_때 {
		final String pinCode = "123456";
		final PlayerDomain host = PlayerDomain.ofNew("호스트");
		final PlayerDomain player1 = PlayerDomain.ofNew("플레이어1");
		final PlayerDomain player2 = PlayerDomain.ofNew("플레이어2");
		final PlayerDomain player3 = PlayerDomain.ofNew("플레이어3");
		final RoomDomain room = RoomDomain.ofNew(pinCode, host);

		@Test
		void 참가자가_없으면_시작할_수_없다() {
			// WHEN
			boolean expected = room.start();

			// THEN
			assertThat(expected).isFalse();
			assertThat(room.getCurrentRound()).isEqualTo(0);
			assertThat(room.getStatus()).isEqualTo(RoomStatus.WAITING);
			assertThat(room.getStartedAt()).isNull();
		}

		@Test
		void 참가자_모두_준비상태가_아니라면_시작할_수_없다() {
			// GIVEN
			player1.ready();
			player2.ready();
			player3.unready();

			room.addPlayer(player1);
			room.addPlayer(player2);
			room.addPlayer(player3);

			// WHEN
			boolean expected = room.start();

			// THEN
			assertThat(expected).isFalse();
			assertThat(room.getCurrentRound()).isEqualTo(0);
			assertThat(room.getStatus()).isEqualTo(RoomStatus.WAITING);
			assertThat(room.getStartedAt()).isNull();
		}

		@Test
		void 참가자가_모두_준비상태면_시작한다() {
			// GIVEN
			player1.ready();
			player2.ready();
			player3.ready();

			room.addPlayer(player1);
			room.addPlayer(player2);
			room.addPlayer(player3);

			// WHEN
			boolean expected = room.start();

			// THEN
			assertThat(expected).isTrue();
			assertThat(room.getCurrentRound()).isEqualTo(1);
			assertThat(room.getStatus()).isEqualTo(RoomStatus.IN_PROGRESS);
			assertThat(room.getStartedAt()).isNotNull();
		}
	}
}
