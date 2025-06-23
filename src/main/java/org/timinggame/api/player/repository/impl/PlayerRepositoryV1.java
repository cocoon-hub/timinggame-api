package org.timinggame.api.player.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.timinggame.api.player.domain.Player;
import org.timinggame.api.player.repository.PlayerRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryV1 implements PlayerRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int getPlayerCount(final Long roomId) {
		throw new RuntimeException("TODO: NoSQL 연결이 필요합니다");
	}

	@Override
	public void saveAll(final List<Player> players) {
		String sql = "INSERT INTO player (nickname, game_room_id) VALUES (?, ?)";
		jdbcTemplate.batchUpdate(sql, players, players.size(), (ps, player) -> {
			ps.setString(1, player.getNickname());
			ps.setLong(2, player.getGameRoomId());
		});
	}
}
