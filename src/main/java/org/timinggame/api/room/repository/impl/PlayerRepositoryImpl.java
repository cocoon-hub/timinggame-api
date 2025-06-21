package org.timinggame.api.room.repository.impl;

import org.springframework.stereotype.Repository;
import org.timinggame.api.room.repository.PlayerRepository;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    @Override
    public int getPlayerCount(final Long roomId) {
        // TODO: Redis 연결
        return 0;
    }
}
