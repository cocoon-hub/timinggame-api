package org.timinggame.api.room.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.timinggame.api.fixture.RoomFixture;

class RoomTest {

    @Test
    void 게임을_시작하면_방의_상태는_진행중으로_변경된다() {
        // GIVEN
        final Long roomId = 1L;
        Room room = RoomFixture.waitingRoom(roomId);

        // WHEN
        room.startGame();

        // THEN
        assertThat(room.getStatus()).isEqualTo(RoomStatus.IN_PROGRESS);
    }
}
