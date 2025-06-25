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

	private static final SecureRandom random = new SecureRandom();
	private static final String PIN_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int DEFAULT_LENGTH = 6;
	private static final int MAX_TIMEOUT = 10;

	private final RedisTemplate<String, Object> redisTemplate;

	public String generateUniquePinCode() {
		String pinCode;
		String redisKeyPrefix = "room:pin-code:";

		do {
			pinCode = generatePinCode(DEFAULT_LENGTH);
		} while (redisTemplate.hasKey(redisKeyPrefix + pinCode));

		redisTemplate.opsForValue().set(redisKeyPrefix + pinCode, "reserved", MAX_TIMEOUT, TimeUnit.MINUTES);

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
