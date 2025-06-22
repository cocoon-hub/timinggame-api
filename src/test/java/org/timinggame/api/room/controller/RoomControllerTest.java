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
import org.timinggame.api.fixture.RoomFixture;
import org.timinggame.api.room.RoomControllerUnitTest;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.exception.NoRoomException;

class RoomControllerTest extends RoomControllerUnitTest {

	final String START_GAME_URL = "/v1/room/start/{roomId}";
	final String ENTER_PIN_CODE_URL = "/v1/room/{pinCode}/enter";
	final String FINISH_GAME_URL = "/v1/room/{roomId}/finish";

	@Test
	void 게임을_시작한다() throws Exception {
		// GIVEN
		final long roomId = 1L;
		final Room room = RoomFixture.inProgressRoom(roomId, LocalDateTime.now(), null);

		// WHEN
		when(roomService.startGame(anyLong())).thenReturn(room);

		// THEN
		mockMvc.perform(post(START_GAME_URL, roomId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.roomId").value(roomId))
			.andDo(print());
	}

	@Test
	void 게임을_시작할_때_존재하지_않는_방이면_예외를_던진다() throws Exception {
		// GIVEN
		final long roomId = 1L;

		// WHEN
		when(roomService.startGame(anyLong())).thenThrow(
			new NoRoomException(String.format("%d번 방은 존재하지 않습니다.", roomId)));

		// THEN
		mockMvc.perform(post(START_GAME_URL, roomId))
			.andExpect(status().isBadRequest())
			.andDo(print());
	}

	@RepeatedTest(5)
	void 방_ID가_0이하면_예외가_발생한다() throws Exception {
		// GIVEN
		final Long roomId = -(new Random().nextLong(Long.MAX_VALUE));
		final Room room = RoomFixture.inProgressRoom(roomId, LocalDateTime.now(), null);

		// THEN
		mockMvc.perform(post(START_GAME_URL, roomId))
			.andExpect(status().isBadRequest())
			.andDo(print());
	}

	@Test
	void 유효한_게임_PIN을_입력한다() throws Exception {
		// GIVEN
		final long roomId = 1L;
		final Room room = RoomFixture.waitingRoom(roomId);
		final String pinCode = room.getPinCode();

		// WHEN
		when(roomService.verifyPinCode(pinCode)).thenReturn(room);

		// THEN
		mockMvc.perform(post(ENTER_PIN_CODE_URL, pinCode))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.roomId").value(roomId))
			.andDo(print());
	}

	@Test
	void 게임을_종료한다() throws Exception {
		// GIVEN
		final long roomId = 1L;
		final Room room = RoomFixture.finishedRoom(roomId);

		// WHEN
		when(roomService.finishGame(anyLong())).thenReturn(room);

		// THEN
		mockMvc.perform(post(FINISH_GAME_URL, roomId))
				.andExpect(status().isOk())
				.andDo(print());
	}
}
