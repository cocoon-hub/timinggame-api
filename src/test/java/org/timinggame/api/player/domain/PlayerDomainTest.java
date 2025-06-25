package org.timinggame.api.player.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerDomainTest {

	@Test
	void 플레이어를_만들_때_닉네임이_필요하다() {
	    // GIVEN
		final String nickname = "플레이어";

	    // WHEN
		PlayerDomain player = PlayerDomain.ofNew(nickname);

		// THEN
		assertThat(player).isNotNull();
		assertThat(player.getNickname()).isEqualTo(nickname);
	}

	@Test
	void 최초_플레이어는_준비하지_않은_상태다() {
		// GIVEN
		final String nickname = "플레이어";

		// WHEN
		PlayerDomain player = PlayerDomain.ofNew(nickname);

		// THEN
		assertThat(player).isNotNull();
		assertThat(player.isReady()).isFalse();
	}

	@Test
	void 준비하면_준비상태가_참이다() {
		// GIVEN
		final String nickname = "플레이어";
		final PlayerDomain player = PlayerDomain.ofNew(nickname);

		// WHEN
		player.ready();

		// THEN
		assertThat(player).isNotNull();
		assertThat(player.isReady()).isTrue();
	}


	@Test
	void 준비해제하면_준비상태가_거짓이다() {
		// GIVEN
		final String nickname = "플레이어";
		final PlayerDomain player = PlayerDomain.ofNew(nickname);

		// WHEN
		player.unready();

		// THEN
		assertThat(player).isNotNull();
		assertThat(player.isReady()).isFalse();
	}
}