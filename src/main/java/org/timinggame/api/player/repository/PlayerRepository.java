package org.timinggame.api.player.repository;

import org.timinggame.api.player.domain.Player;

import java.util.List;

public interface PlayerRepository {

	int getPlayerCount(final Long roomId);

	void saveAll(final List<Player> player);
}
