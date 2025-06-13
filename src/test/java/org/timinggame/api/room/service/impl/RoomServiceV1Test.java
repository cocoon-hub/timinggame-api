package org.timinggame.api.room.service.impl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.timinggame.api.fixture.RoomFixture;
import org.timinggame.api.room.RoomServiceUnitTest;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.domain.RoomStatus;
import org.timinggame.api.room.exception.NoRoomException;

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
        when(roomRepository.findById(roomId)).thenReturn(Optional.ofNullable(null));

        // THEN
        assertThatThrownBy(() -> roomService.startGame(roomId))
                .isInstanceOf(NoRoomException.class)
                .hasMessage(String.format("%d번 방은 존재하지 않습니다.", roomId));
    }
}
