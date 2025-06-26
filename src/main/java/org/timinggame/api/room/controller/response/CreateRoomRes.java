package org.timinggame.api.room.controller.response;

import org.timinggame.api.player.domain.PlayerDomain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRoomRes {

	private String roomId;
	private String pinCode;
	private PlayerDomain host;
}
