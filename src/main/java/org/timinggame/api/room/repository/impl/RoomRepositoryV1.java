package org.timinggame.api.room.repository.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.repository.RoomRepository;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryV1 implements RoomRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public Optional<Room> findById(final Long roomId) {
		String sql = """
			SELECT game_room_id as room_id,
									       pin_code,
									       status,
									       loser_id,
									       started_at,
									       finished_at,
									       created_at
			FROM game_room WHERE game_room_id = ?;
			""";
		return Optional.ofNullable(
			jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Room.class), roomId));
	}

	@Override
	public void startGame(final Room room) {
		String sql = "UPDATE game_room SET status = ? WHERE game_room_id = ?";
		jdbcTemplate.update(sql, room.getStatus().getValue(), room.getRoomId());
	}

	@Override
	public Optional<Room> findByPinCode(final String pinCode) {
		String sql = """
			SELECT game_room_id as room_id,
									       pin_code,
									       status,
									       loser_id,
									       started_at,
									       finished_at,
									       created_at
			FROM game_room WHERE pin_code = ?;
			""";
		return Optional.ofNullable(
			jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Room.class), pinCode));
	}
}
