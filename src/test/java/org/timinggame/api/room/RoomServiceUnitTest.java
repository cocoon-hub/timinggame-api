package org.timinggame.api.room;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.timinggame.api.ServiceUnitTest;
import org.timinggame.api.player.repository.PlayerRepository;
import org.timinggame.api.room.repository.RoomRepository;
import org.timinggame.api.room.repository.redis.RoomRedisRepository;
import org.timinggame.api.room.service.impl.RoomServiceV1;
import org.timinggame.api.room.util.PinCodeAdvisor;

public abstract class RoomServiceUnitTest extends ServiceUnitTest {

	@InjectMocks
	protected RoomServiceV1 roomService;

	@Mock
	protected RoomRepository roomRepository;

	@Mock
	protected PlayerRepository playerRepository;

	@Mock
	protected RoomRedisRepository roomRedisRepository;

	@Mock
	protected PinCodeAdvisor pinCodeAdvisor;
}
