package org.timinggame.api.room.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.exception.NoRoomException;
import org.timinggame.api.room.repository.RoomRepository;
import org.timinggame.api.room.service.RoomService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceV1 implements RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    @Override
    public Room startGame(final Long roomId) {
        Room room =
                roomRepository
                        .findById(roomId)
                        .orElseThrow(
                                () ->
                                        new NoRoomException(
                                                String.format("%d번 방은 존재하지 않습니다.", roomId)));
        room.startGame();
        roomRepository.startGame(room);
        return room;
    }
}
