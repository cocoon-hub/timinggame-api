package org.timinggame.api.room.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.timinggame.api.room.controller.response.EnterPinCodeRes;
import org.timinggame.api.room.controller.response.StartGameRes;
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
	public ResponseEntity<StartGameRes> startGame(@PathVariable("roomId") @Positive
	Long roomId) {
		Room room = roomService.startGame(roomId);
		return ResponseEntity.ok(StartGameRes.builder().roomId(room.getRoomId()).build());
	}

	@PostMapping("/{pinCode}/enter")
	public ResponseEntity<EnterPinCodeRes> enterPinCode(@PathVariable("pinCode")
	final String pinCode) {
		Room room = roomService.verifyPinCode(pinCode);
		return ResponseEntity.ok(EnterPinCodeRes.builder().roomId(room.getRoomId()).build());
	}
}
