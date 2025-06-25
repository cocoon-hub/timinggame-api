package org.timinggame.api.room.repository.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoomRedisRepository {

	private final RedisTemplate<String, Object> redisTemplate;
	private final String PREFIX = "room:";

	public void save(final RoomDto room) {
		redisTemplate.opsForValue().set(PREFIX + room.getId(), room);
	}
}
