package org.timinggame.api.room.service.impl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.timinggame.api.fixture.RoomFixture;
import org.timinggame.api.room.RoomServiceUnitTest;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.domain.RoomStatus;
import org.timinggame.api.room.exception.*;

class RoomServiceV1Test extends RoomServiceUnitTest {

	@Test
	void 게임을_시작할_때_방_상태를_진행중으로_변경한다() {
		// GIVEN
		final Long roomId = 1L;
		final Room expect = RoomFixture.waitingRoom(roomId);

		// WHEN
		when(roomRepository.findById(roomId)).thenReturn(Optional.ofNullable(expect));
		Room actual = roomService.startGame(roomId);

		// THEN
		assertThat(actual).isNotNull();
		assertThat(actual).isEqualTo(expect);
		assertThat(actual.getStatus()).isEqualTo(RoomStatus.IN_PROGRESS);
	}

	@Test
	void 게임을_시작할_때_존재하지_않는_방이면_예외를_던진다() {
		// GIVEN
		final Long roomId = 1L;

		// WHEN
		when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

		// THEN
		assertThatThrownBy(() -> roomService.startGame(roomId))
			.isInstanceOf(NoRoomException.class)
			.hasMessage(String.format("%d번 방은 존재하지 않습니다.", roomId));
	}

	@Test
	void PIN을_입력할_때_유효한_코드면_통과한다() {
		// GIVEN
		final Long roomId = 1L;
		final Room expect = RoomFixture.waitingRoom(roomId);
		final String pinCode = expect.getPinCode();
		final int CURRENT_PLAYERS = 1;

		// WHEN
		when(roomRepository.findByPinCode(pinCode)).thenReturn(Optional.of(expect));
		when(playerRepository.getPlayerCount(roomId)).thenReturn(CURRENT_PLAYERS);
		Room actual = roomService.verifyPinCode(pinCode);

		// THEN
		assertThat(actual).isNotNull();
		assertThat(actual).isEqualTo(expect);
	}

	@Test
	void PIN을_입력할_때_존재하지_않는_코드면_예외를_던진다() {
		// GIVEN
		final String invalidPinCode = "1234";

		// WHEN
		when(roomRepository.findByPinCode(invalidPinCode)).thenReturn(Optional.empty());

		// THEN
		assertThatThrownBy(() -> roomService.verifyPinCode(invalidPinCode))
			.isInstanceOf(NoPinCodeException.class)
			.hasMessage(String.format("%s는 존재하지 않는 코드입니다.", invalidPinCode));
	}

	@Test
	void PIN을_입력할_때_이미_게임이_시작된_방이면_예외를_던진다() {
		// GIVEN
		final Long roomId = 1L;
		final Room expect = RoomFixture.inProgressRoom(roomId, LocalDateTime.now(), null);
		String pinCode = expect.getPinCode();

		// WHEN
		when(roomRepository.findByPinCode(pinCode)).thenReturn(Optional.of(expect));

		// THEN
		assertThatThrownBy(() -> roomService.verifyPinCode(pinCode))
			.isInstanceOf(AlreadyGameStartedException.class)
			.hasMessage(String.format("%d번 방은 이미 게임이 시작되었습니다.", roomId));
	}

	@Test
	void PIN을_입력할_때_인원이_가득_찬_방이면_예외를_던진다() {
		// GIVEN
		final Long roomId = 1L;
		final Room expect = RoomFixture.waitingRoom(roomId);
		String pinCode = expect.getPinCode();
		final int MAX_PLAYERS_PER_ROOM = 50;

		// WHEN
		when(roomRepository.findByPinCode(pinCode)).thenReturn(Optional.of(expect));
		when(playerRepository.getPlayerCount(roomId)).thenReturn(MAX_PLAYERS_PER_ROOM);

		// THEN
		assertThatThrownBy(() -> roomService.verifyPinCode(pinCode))
			.isInstanceOf(RoomExceededException.class)
			.hasMessage(String.format("%d번 방은 인원이 가득 찼습니다.", roomId));
	}

	@Test
	void 게임을_종료할_때_방_상태를_FINISHED로_변경한다() {
		// GIVEN
		final Long roomId = 1L;
		final Room expect = RoomFixture.inProgressRoom(roomId, null, null);

		// WHEN
		when(roomRepository.findById(roomId)).thenReturn(Optional.ofNullable(expect));
		Room actual = roomService.finishGame(roomId);

		// THEN
		assertThat(actual).isNotNull();
		assertThat(actual).isEqualTo(expect);
		assertThat(actual.getStatus()).isEqualTo(RoomStatus.FINISHED);
	}

	@Test
	void 게임을_종료할_때_존재하지_않는_방이면_예외를_던진다() {
		// GIVEN
		final Long roomId = 1L;

		// WHEN
		when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

		// THEN
		assertThatThrownBy(() -> roomService.finishGame(roomId))
			.isInstanceOf(NoRoomException.class)
			.hasMessage(String.format("%d번 방은 존재하지 않습니다.", roomId));
	}

	@Test
	void 게임을_종료할_때_이미_종료된_방이면_예외를_던진다() {
		final Long roomId = 1L;
		final Room expect = RoomFixture.finishedRoom(roomId);

		// WHEN
		when(roomRepository.findById(roomId)).thenReturn(Optional.ofNullable(expect));

		// THEN
		assertThatThrownBy(() -> roomService.finishGame(roomId))
			.isInstanceOf(AlreadyGameFinishedException.class)
			.hasMessage(String.format("%d번 방은 이미 게임이 종료되었습니다.", roomId));
	}
}
