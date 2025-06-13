package org.timinggame.api.room.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.timinggame.api.RoomControllerUnitTest;
import org.timinggame.api.fixture.RoomFixture;
import org.timinggame.api.room.domain.Room;

class RoomControllerTest extends RoomControllerUnitTest {

	final String START_GAME_URL = "/v1/room/start/{roomId}";

	@Test
	void 게임을_시작한다() throws Exception {
		// GIVEN
		final long roomId = 1L;
		final Room room = RoomFixture.inProgressRoom(LocalDateTime.now(), null);

		// WHEN
		when(roomService.startGame(anyLong())).thenReturn(room);

		// THEN
		mockMvc.perform(post(START_GAME_URL, roomId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.roomId").value(roomId))
			.andDo(print());
	}

	@RepeatedTest(5)
	void 방_ID가_0이하면_예외가_발생한다() throws Exception {
		// GIVEN
		final Long roomId = -(new Random().nextLong(Long.MAX_VALUE));
		final Room room = RoomFixture.inProgressRoom(LocalDateTime.now(), null);

		// WHEN
		when(roomService.startGame(anyLong())).thenReturn(room);

		// THEN
		mockMvc.perform(post(START_GAME_URL, roomId))
			.andExpect(status().isBadRequest())
			.andDo(print());
	}
}