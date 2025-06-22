package org.timinggame.api.common.exception;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.timinggame.api.room.exception.RoomException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionRes> handleConstraintViolationException(HttpServletRequest req,
		ConstraintViolationException ex) {
		printLogFailedRequest(req);
		String message = ex.getConstraintViolations().stream()
			.map(violation -> violation.getMessage())
			.collect(Collectors.joining(", "));
		return ResponseEntity.badRequest().body(
			new ExceptionRes(req.getRequestURL().toString(), "유효성 검증에 실패했습니다.", message));
	}

	@ExceptionHandler(RoomException.class)
	public ResponseEntity<ExceptionRes> handlerRoomException(HttpServletRequest req, RoomException ex) {
		printLogFailedRequest(req);
		return ResponseEntity.status(ex.getStatus()).body(
			new ExceptionRes(req.getRequestURL().toString(), ex.getCause().toString(), ex.getMessage()));
	}

	private void printLogFailedRequest(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		String httpMethod = req.getMethod();
		String requestURL = req.getRequestURL().toString();
		String failedRequestInfo = sb.append(httpMethod).append(" :: ").append(requestURL).toString();
		log.error(failedRequestInfo);
	}
}
