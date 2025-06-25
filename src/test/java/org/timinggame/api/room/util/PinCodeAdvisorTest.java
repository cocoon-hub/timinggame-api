package org.timinggame.api.room.util;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@ExtendWith(MockitoExtension.class)
class PinCodeAdvisorTest {

	@InjectMocks
	private PinCodeAdvisor pinCodeAdvisor;

	@Mock
	private RedisTemplate<String, Object> redisTemplate;

	@Mock
	private ValueOperations<String, Object> valueOperations;

	@BeforeEach
	void setUp() {
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
	}

	@Test
	void PIN_CODE는_6자리다() {
		// WHEN
		when(redisTemplate.hasKey(anyString())).thenReturn(false);
		doNothing().when(valueOperations).set(anyString(), any(), eq(10L), eq(TimeUnit.MINUTES));

		String pinCode = pinCodeAdvisor.generateUniquePinCode();

		// THEN
		assertThat(pinCode).hasSize(6);
		verify(redisTemplate).hasKey("room:pin-code:" + pinCode);
		verify(valueOperations).set("room:pin-code:" + pinCode, "reserved", 10, TimeUnit.MINUTES);
	}

	@Test
	void PIN_CODE가_중복되지_않을_때까지_코드를_생성한다() {
		// WHEN
		when(redisTemplate.hasKey(anyString()))
			.thenReturn(true)
			.thenReturn(false);
		doNothing().when(valueOperations).set(anyString(), any(), eq(10L), eq(TimeUnit.MINUTES));

		String pinCode = pinCodeAdvisor.generateUniquePinCode();

		// THEN
		verify(redisTemplate, times(1)).hasKey("room:pin-code:" + pinCode);
	}

}
