package org.timinggame.api.room.repository;

import java.util.Optional;
import org.timinggame.api.room.domain.Room;

public interface RoomRepository {

	Optional<Room> findById(final Long roomId);

	void startGame(final Room room);

	Optional<Room> findByPinCode(final String pinCode);
}
