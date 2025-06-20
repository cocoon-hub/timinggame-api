package org.timinggame.api.room.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.timinggame.api.room.controller.response.CreateRoomRes;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.service.RoomService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1/room")
@RestController
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	@PostMapping("/start/{roomId}")
	public ResponseEntity<CreateRoomRes> startGame(@PathVariable("roomId") @Positive
	Long roomId) {
		Room room = roomService.startGame(roomId);
		return ResponseEntity.ok(CreateRoomRes.builder().roomId(room.getRoomId()).build());
	}
}
