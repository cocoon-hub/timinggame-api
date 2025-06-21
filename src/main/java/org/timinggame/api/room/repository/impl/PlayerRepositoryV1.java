package org.timinggame.api.room.repository.impl;

import org.springframework.stereotype.Repository;
import org.timinggame.api.room.repository.PlayerRepository;

@Repository
public class PlayerRepositoryV1 implements PlayerRepository {

    @Override
    public int getPlayerCount(final Long roomId) {
        return 0;
    }
}
