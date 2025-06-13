package org.timinggame.api.fixture;

import java.time.LocalDateTime;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.domain.RoomStatus;

public final class RoomFixture {

    public static Room waitingRoom() {
        return Room.builder()
                .roomId(1L)
                .pinCode("123456789")
                .status(RoomStatus.WAITING)
                .startedAt(null)
                .finishedAt(null)
                .loserId(null)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
