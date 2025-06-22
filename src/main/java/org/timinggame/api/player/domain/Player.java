package org.timinggame.api.player.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player {

	private Long playerId;
	private String nickname;
	private Long gameRoomId;
	private LocalDateTime createdAt;
}
