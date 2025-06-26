package org.timinggame.api.player.domain;

import java.util.Objects;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerDomain {

	private String id;
	private String nickname;
	private boolean ready;

	public static PlayerDomain ofNew(final String nickname) {
		String id = UUID.randomUUID().toString();
		return PlayerDomain.builder().id(id).nickname(nickname).ready(false).build();
	}

	public void ready() {
		this.ready = true;
	}

	public void unready() {
		this.ready = false;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		PlayerDomain that = (PlayerDomain)o;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}
}
