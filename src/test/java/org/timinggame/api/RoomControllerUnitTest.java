package org.timinggame.api;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.timinggame.api.room.service.RoomService;

public class RoomControllerUnitTest extends ControllerUnitTest {

	@MockitoBean
	protected RoomService roomService;
}
