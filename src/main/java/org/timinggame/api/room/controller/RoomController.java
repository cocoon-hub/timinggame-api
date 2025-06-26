package org.timinggame.api.room.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.timinggame.api.room.controller.response.CreateRoomRes;
import org.timinggame.api.room.controller.response.EnterPinCodeRes;
import org.timinggame.api.room.controller.response.FinishRoomRes;
import org.timinggame.api.room.controller.response.StartGameRes;
import org.timinggame.api.room.domain.Room;
import org.timinggame.api.room.domain.RoomDomain;
import org.timinggame.api.room.service.RoomService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Validated
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

	@PostMapping("/{roomId}/finish")
	public ResponseEntity<FinishRoomRes> finishGame(@PathVariable("roomId") @Positive
	final Long roomId) {
		Room room = roomService.finishGame(roomId);
		return ResponseEntity.ok(FinishRoomRes.builder().loserId(room.getLoserId()).build());
	}

	@PostMapping("/create")
	public ResponseEntity<CreateRoomRes> createGame(@RequestParam("nickname")
	final String nickname) {
		RoomDomain room = roomService.createRoom(nickname);
		return ResponseEntity.ok(CreateRoomRes.builder()
			.roomId(room.getId())
			.pinCode(room.getPinCode())
			.host(room.getHost())
			.build());
	}
}
