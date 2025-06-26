package org.timinggame.api.room.util;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public final class PinCodeAdvisor {

	private final SecureRandom random = new SecureRandom();
	private final String PIN_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final String PIN_CODE_PREFIX = "room:pin-code:";
	private final int DEFAULT_LENGTH = 6;
	private final int MAX_TIMEOUT = 10;

	private final RedisTemplate<String, Object> redisTemplate;

	public String generateUniquePinCode() {
		String pinCode;

		do {
			pinCode = generatePinCode(DEFAULT_LENGTH);
		} while (redisTemplate.hasKey(PIN_CODE_PREFIX + pinCode));

		redisTemplate.opsForValue().set(PIN_CODE_PREFIX + pinCode, "reserved", MAX_TIMEOUT, TimeUnit.MINUTES);

		return pinCode;
	}

	private String generatePinCode(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(PIN_CHARS.length());
			sb.append(PIN_CHARS.charAt(index));
		}
		return sb.toString();
	}
}
