package org.timinggame.api.room.service;

import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.domain.RoomDomain;

public interface RoomService {

	Room startGame(final Long roomId);

	Room verifyPinCode(final String pinCode);

	Room finishGame(final Long roomId);

	RoomDomain createRoom(final String nickname);
}
