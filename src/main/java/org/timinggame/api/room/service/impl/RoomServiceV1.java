package org.timinggame.api.room.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.exception.NoRoomException;
import org.timinggame.api.room.repository.RoomRepository;
import org.timinggame.api.room.service.RoomService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class RoomServiceV1 implements RoomService {

	private final RoomRepository roomRepository;

	@Transactional
	@Override
	public Room startGame(final Long roomId) {
		Room room = roomRepository.findById(roomId)
			.orElseThrow(() -> new NoRoomException("존재하지 않은 방입니다."));
		room.startGame();
		roomRepository.startGame(room);
		return room;
	}
}
