package org.timinggame.api.room.repository.impl;

import org.springframework.stereotype.Repository;
import org.timinggame.api.room.repository.PlayerRepository;

@Repository
public class PlayerRepositoryV1 implements PlayerRepository {

    @Override
    public int getPlayerCount(final Long roomId) {
        throw new RuntimeException("TODO: NoSQL 연결이 필요합니다");
    }
}
