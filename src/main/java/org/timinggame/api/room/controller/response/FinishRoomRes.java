package org.timinggame.api.room.controller.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FinishRoomRes {

	private Long loserId;
}
