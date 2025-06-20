package org.timinggame.api.room.service;

import org.timinggame.api.room.domain.Room;

public interface RoomService {

	Room startGame(final Long roomId);

    Room verifyPinCode(String pinCode);
}
