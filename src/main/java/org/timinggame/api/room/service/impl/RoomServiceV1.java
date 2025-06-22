package org.timinggame.api.room.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.timinggame.api.player.domain.Player;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.exception.*;
import org.timinggame.api.player.repository.PlayerRepository;
import org.timinggame.api.room.repository.RoomRepository;
import org.timinggame.api.room.service.RoomService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceV1 implements RoomService {

	private final int MAX_PLAYERS_PER_ROOM = 50;

	private final RoomRepository roomRepository;
	private final PlayerRepository playerRepository;

	@Transactional
	@Override
	public Room startGame(final Long roomId) {
		Room room = roomRepository.findById(roomId)
			.orElseThrow(() -> new NoRoomException(String.format("%d번 방은 존재하지 않습니다.", roomId)));
		room.startGame();
		roomRepository.startGame(room);
		return room;
	}

	@Override
	public Room verifyPinCode(final String pinCode) {
		Room room = roomRepository
			.findByPinCode(pinCode)
			.orElseThrow(
				() -> new NoPinCodeException(
					String.format("%s는 존재하지 않는 코드입니다.", pinCode)));

		Long roomId = room.getRoomId();

		if (room.isStarted()) {
			throw new AlreadyGameStartedException(String.format("%d번 방은 이미 게임이 시작되었습니다.", roomId));
		}

		if (playerRepository.getPlayerCount(roomId) == MAX_PLAYERS_PER_ROOM) {
			throw new RoomExceededException(String.format("%d번 방은 인원이 가득 찼습니다.", roomId));
		}

		return room;
	}

	@Transactional
	@Override
	public Room finishGame(final Long roomId) {
		Room room = roomRepository.findById(roomId)
			.orElseThrow(() -> new NoRoomException(String.format("%d번 방은 존재하지 않습니다.", roomId)));

		if (room.isFinished()) {
			throw new AlreadyGameFinishedException(String.format("%d번 방은 이미 게임이 종료되었습니다.", roomId));
		}

		room.finishGame();
		roomRepository.finishGame(room);
		// TODO: players를 MongoDB에서 가져와야 합니다
		List<Player> players = new ArrayList<>();
		playerRepository.saveAll(players);
		return room;
	}
}
